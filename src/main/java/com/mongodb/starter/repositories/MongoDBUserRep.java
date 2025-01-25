package com.mongodb.starter.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.starter.dtos.TicketDTO;
import com.mongodb.starter.model.Event;
import com.mongodb.starter.model.Ticket;
import com.mongodb.starter.model.User;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.ReturnDocument.AFTER;

@Repository
public class MongoDBUserRep implements UserRepository{

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
    private final MongoClient client;
    private MongoCollection<User> userCollection;

    public MongoDBUserRep(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        userCollection = client.getDatabase("AppEntradass").getCollection("Users", User.class);
    }


    @Override
    public User save(User userEntity) {

        userEntity.setId(new ObjectId());
        userCollection.insertOne(userEntity);
        return userEntity;
    }

    @Override
    public List<User> saveAll(List<User> userEntities) {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(() -> {
                userEntities.forEach(p -> p.setId(new ObjectId()));
                userCollection.insertMany(clientSession, userEntities);
                return userEntities;
            }, txnOptions);
        }
    }

    @Override
    public List<User> findAll() {
        return userCollection.find().into(new ArrayList<>());
    }

    @Override
    public List<User> findAll(List<String> ids) {
        return userCollection.find(in("_id", mapToObjectIds(ids))).into(new ArrayList<>());
    }

    @Override
    public User findOne(String id) {
        User foundUser = null;
        foundUser = userCollection.find(eq("_id", new ObjectId(id))).first();
        return foundUser;
    }

    public User findUserWithTicket(String id) {
        User foundUser = null;
        foundUser = userCollection.find(eq("tickets._id", new ObjectId(id))).first();
        return foundUser;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public long delete(String id) {
        return 0;
    }

    @Override
    public User update(User userEntity) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        return userCollection.findOneAndReplace(eq("_id", userEntity.getId()), userEntity, options);
    }

    @Override
    public long update(List<User> userEntities) {
        List<ReplaceOneModel<User>> writes = userEntities.stream()
                .map(u -> new ReplaceOneModel<>(eq("_id", u.getId()),
                        u))
                .toList();
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> userCollection.bulkWrite(clientSession, writes).getModifiedCount(), txnOptions);
        }

    }

    @Override
    public User purchaseTicket(User user, Event event) {
        MongoDBEventRep eventRepository = new MongoDBEventRep(client);
        eventRepository.init();
        MongoDBTicketRep ticketRepository = new MongoDBTicketRep(client);
        ticketRepository.init();
        if(userExists(user) && eventRepository.eventExists(event)){
            if(eventRepository.eventHasAvailableTickets(event)){
                return findOne(
                        ticketRepository.generateTicket(user, event)
                                .getUserId()
                                .toHexString()
                );
            }
        }
        return null;
    }
    @Override
    public TicketDTO adminValidateTicket(User user, Ticket ticket) {
        if(userExists(user)){
            User u = findOne(user.getId().toHexString());
            if (u.isAdmin()){
                MongoDBTicketRep ticketRepository = new MongoDBTicketRep(client);
                ticketRepository.init();
                return new TicketDTO(ticketRepository.validateTicket(ticket));
            }

        }
        return null;
    }

    @Override
    public boolean addTicket(User user, Ticket ticket) {
        if (userExists(user)) {
            List<Ticket> tickets = new ArrayList<>(user.getTickets());
            tickets.add(ticket);
            user.setTickets(tickets);
            update(user);
        }
        return false;
    }

    @Override
    //todo
    public List<Ticket> getUserTickets(User user) {
        if(userExists(user)){
            return findOne(user.getId().toHexString()).getTickets();
        }
        return null;
    }

    @Override
    //todo
    public List<Event> getUserEvents(User user) {
        if(userExists(user)){
            User u = findOne(user.getId().toHexString());
            if (u.isAdmin()){
                return findOne(user.getId().toHexString()).getEvents();
            }
            return new ArrayList<Event>();
        }
        return null;

    }

    //todo puede que funcione, sin probar
    public TicketDTO adminValidateTickett(User user, Ticket ticket) {
        if(userExists(user)){
            User u = findOne(user.getId().toHexString());
            if (u.isAdmin()){
                TicketRepository ticketRepository = new MongoDBTicketRep(client);
                EventRepository eventRepository = new MongoDBEventRep(client);
                Ticket t = ticketRepository.findTicket(ticket.getId().toHexString());
                if (u.getEvents().stream().anyMatch(event -> event.getId().equals(t.getEventId()))) {
                    return new TicketDTO(ticketRepository.validateTicket(ticket));
                }
            }
        }
        return null;
    }

    @Override
    public User findByLogin(String username, String password) {
        User foundUser = null;
        foundUser = userCollection.find(Filters.and(
                        eq("username", username),
                        eq("password", password)
                )
        ).first();
        return foundUser;
    }


    private List<ObjectId> mapToObjectIds(List<String> ids) {
        return ids.stream().map(ObjectId::new).toList();
    }
    public boolean userExists(User user){
        User foundUser = null;
        foundUser = userCollection.find(eq("_id", user.getId())).first();
        return foundUser != null;
    }
}
