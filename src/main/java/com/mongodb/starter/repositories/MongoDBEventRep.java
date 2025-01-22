package com.mongodb.starter.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.starter.model.Event;
import com.mongodb.starter.model.Ticket;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.ReturnDocument.AFTER;

@Repository
public class MongoDBEventRep implements EventRepository{

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
    private final MongoClient client;
    private MongoCollection<Event> eventCollection;

    public MongoDBEventRep(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        eventCollection = client.getDatabase("AppEntradass").getCollection("Events", Event.class);
    }
    @Override
    public boolean eventExists(Event event) {
        Event foundEvent = null;
        foundEvent = eventCollection.find(eq("_id", event.getId())).first();
        return foundEvent != null;
    }

    @Override
    public Event update(Event eventEntity) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        return eventCollection.findOneAndReplace(eq("_id", eventEntity.getId()), eventEntity, options);
    }

    @Override
    public List<Event> findAll(List<String> ids) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public List<Event> UserFindAll() {
        return null;
    }

    @Override
    public boolean addTicket(Event event, Ticket t) {
        if (eventExists(event)){
            event.getTickets().add(t);
            update(event);
            return true;
        }
        return false;
    }
    @Override
    public boolean eventHasAvailableTickets(Event event){
        if (eventExists(event)){
            return event.getTickets().size() < event.getCapacity();
        }
        return false;
    }
}
