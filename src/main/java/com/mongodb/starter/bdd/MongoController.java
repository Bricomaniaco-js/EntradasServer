package com.mongodb.starter.bdd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.starter.model.Event;
import com.mongodb.starter.model.Ticket;
import com.mongodb.starter.model.User;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

public class MongoController {
/*    private static MongoDatabase db;
    public MongoController( MongoDatabase db){
        this.db = db;
    }
    public ArrayList<Ticket> retrieveUserTickets(String username, String password){
        MongoCollection<Document> users = db.getCollection("Users");
        Document foundUser = null;
        foundUser = users.find(new Document()
                .append("username", username)
                .append("password", password)
        ).first();
        ArrayList<Ticket> tickets = new ArrayList<>();
        if(!listIsEmpty(foundUser.getList("tickets", Document.class))) {
            for (Document t : foundUser.getList("tickets", Document.class)) {
                tickets.add(toTicket(t));
            }
        }
        return tickets;
    }
    
    public Ticket toTicket(Document d){
        return new Ticket(d.getInteger("id"));
    }

    public ArrayList<Event> retrieveUserEvents(String username, String password){
        MongoCollection<Document> users = db.getCollection("Users");
        Document foundUser = null;
        foundUser = users.find(new Document()
                .append("username", username)
                .append("password", password)
        ).first();
        ArrayList<Event> events = new ArrayList<>();
        if(!listIsEmpty(foundUser.getList("events", Document.class))) {
            for(Document e : foundUser.getList("events", Document.class)){
                events.add(toEvent(e));
            }
        }
        return events;
    }

    public Event toEvent(Document d){
        ArrayList<Ticket> tickets = retrieveEventTickets(d.getInteger("id"));
        return new Event(
                d.getInteger("id"),
                d.getString("name"), 
                d.getString("description"), 
                tickets);
    }

    private ArrayList<Ticket> retrieveEventTickets(Integer id) {
        MongoCollection<Document> Events = db.getCollection("Events");
        Document foundEvent = null;
        foundEvent = Events.find(new Document()
                .append("id", id)
        ).first();
        ArrayList<Ticket> tickets = new ArrayList<>();
        if(!listIsEmpty(foundEvent.getList("tickets", Document.class))) {
            for(Document t : foundEvent.getList("tickets", Document.class)){
                tickets.add(toTicket(t));
            }
        }
        return tickets;
    }

    public User getUser(String username, String password){
        if(userExists(username)) {
            MongoCollection<Document> users = db.getCollection("Users");
            Document foundUser = null;
            foundUser = users.find(new Document()
                    .append("username", username)
                    .append("password", password)
            ).first();

            ArrayList<Ticket> tickets = retrieveUserTickets(username, password);
            ArrayList<Event> events = retrieveUserEvents(username, password);


            return new User(
                    //TODO foundUser.getLong("id"),
                    foundUser.getString("username"),
                    foundUser.getString("password"),
                    tickets,
                    events,
                    foundUser.getBoolean("isAdmin")==true);
        }
        return null;
    }
    public boolean listIsEmpty(List l){
        if(l == null){
            return true;
        }
        return l.isEmpty();
    }
    public boolean userExists (String username){
        MongoCollection<Document> users = db.getCollection("Users");
        Document foundUser = null;
        foundUser = users.find(new Document()
                .append("username", username)
        ).first();
        return foundUser != null;
    }
    public boolean addUserToDatabase(User u){
        //todo: verificar que existen eventos y entradas
        if(!userExists(u.getUsername())){
            MongoCollection<Document> users = db.getCollection("Users");
            users.insertOne(u.toDocument());
            return true;
        }
        return false;
    }

    public boolean createNewUser(String username, String password, Boolean isAdmin){
        if(!userExists(username)) {
            User u = new User(username, password);
            u.setAdmin(isAdmin);
            addUserToDatabase(u);
            return true;
        }
        return false;
    }
    public boolean addEventsToUser(){
        //TODO
        return false;
    }
    public boolean addTicketToUser(){
        //TODO
        return false;
    }*/
}
