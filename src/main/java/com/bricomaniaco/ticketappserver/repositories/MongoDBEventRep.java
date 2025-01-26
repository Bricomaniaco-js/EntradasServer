package com.bricomaniaco.ticketappserver.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.bricomaniaco.ticketappserver.model.Event;
import com.bricomaniaco.ticketappserver.model.Ticket;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.ReturnDocument.AFTER;

/**
 * Repository for managing Event entities in MongoDB.
 */
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
        Event foundEvent = eventCollection.find(eq("_id", event.getId())).first();
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
        return eventCollection.find().into(new ArrayList<>());
    }

    @Override
    public List<Event> userFindAll() {
        //retorna todos los eventos pero no sus tickets
        return eventCollection.find().map(event -> {
            event.setTickets(new ArrayList<>());
            return event;
        }).into(new ArrayList<>());
    }


    @Override
    public boolean addTicket(Event event, Ticket t) {
        if (eventExists(event)){
            Event e = userFindOne(event.getId().toHexString());
            List<Ticket> tickets = new ArrayList<>(e.getTickets());
            tickets.add(t);
            e.setTickets(tickets);
            update(e);
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


    @Override
    public Event save(Event event) {
        event.setId(new ObjectId());
        eventCollection.insertOne(event);
        return event;
    }

    @Override
    public Event userFindOne(String id) {
        Event foundEvent = null;
        foundEvent = eventCollection.find(eq("_id", new ObjectId(id))).first();
        if (foundEvent == null) return null;
        foundEvent.setTickets(new ArrayList<>());
        return foundEvent;
    }
}
