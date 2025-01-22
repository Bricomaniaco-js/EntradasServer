package com.mongodb.starter.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.starter.model.Event;
import com.mongodb.starter.model.Ticket;
import com.mongodb.starter.model.User;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.ReturnDocument.AFTER;

public class MongoDBTicketRep implements TicketRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
    private final MongoClient client;
    private MongoCollection<Ticket> ticketCollection;

    public MongoDBTicketRep(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        ticketCollection = client.getDatabase("AppEntradass").getCollection("Tickets", Ticket.class);
    }


    @Override
    public boolean ticketExists(Ticket ticket) {
        Ticket foundTicket = null;
        foundTicket = ticketCollection.find(eq("_id", ticket.getId())).first();
        return foundTicket != null;
    }

    @Override
    public Ticket generateTicket(User user, Event event) {
        UserRepository userRepository = new MongoDBUserRep(client);
        EventRepository eventRepository = new MongoDBEventRep(client);
        Ticket t = new Ticket(new ObjectId());
        while (ticketExists(t)){
            t = new Ticket(new ObjectId());
        }
        ticketCollection.insertOne(t);
        userRepository.addTicket(user, t);
        eventRepository.addTicket(event, t);
        return t;
    }

    @Override
    public Ticket validateTicket(Ticket ticket) {
        if(ticketExists(ticket)){
            Ticket t = ticketCollection.find(eq("_id", ticket.getId())).first();
            if (t.isValid()){
                t.setValid(false);
                update(t);
                return t;
            }
        }
        return null;
    }

    @Override
    public Event getTicketEvent(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket findTicket(String hexString) {
        Ticket foundTicket = null;
        foundTicket = ticketCollection.find(eq("_id", new ObjectId(hexString))).first();
        return foundTicket;
    }

    public Ticket update(Ticket ticket) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        return ticketCollection.findOneAndReplace(eq("_id", ticket.getId()), ticket, options);
    }
}
