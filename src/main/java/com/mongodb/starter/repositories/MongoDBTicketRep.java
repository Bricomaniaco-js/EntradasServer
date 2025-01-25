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
import com.mongodb.starter.model.User;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;

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
        MongoDBUserRep userRepository = new MongoDBUserRep(client);
        userRepository.init();
        MongoDBEventRep eventRepository = new MongoDBEventRep(client);
        eventRepository.init();
        Ticket t = new Ticket(new ObjectId());
        while (ticketExists(t)){
            t = new Ticket(new ObjectId());
        }
        t.setValid(true);
        t.setUserId(user.getId());
        t.setEventId(event.getId());
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
                MongoDBUserRep userRepository = new MongoDBUserRep(client);
                userRepository.init();
                User u = userRepository.findOne(t.getUserId().toHexString());

                MongoDBEventRep eventRepository = new MongoDBEventRep(client);
                eventRepository.init();
                Event e = eventRepository.userFindOne(t.getEventId().toHexString());

                t.setValid(false);
                for (Ticket ticket1 : u.getTickets()) {
                    if (ticket1.getId().equals(t.getId())){
                        ticket1.setValid(false);
                    }
                }
                for (Ticket ticket1 : e.getTickets()) {
                    if (ticket1.getId().equals(t.getId())) {
                        ticket1.setValid(false);
                    }
                }

                userRepository.update(u);
                eventRepository.update(e);
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
