package com.mongodb.starter.model;
import org.bson.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

import com.mongodb.starter.bdd.MongoInterface;

public class User implements Serializable {


    ObjectId id;
    String username;
    String password;
    List<Ticket> tickets;

    public boolean isAdmin;


    List<Event> events;

    public ObjectId getId() {
        return id;
    }

    public User setId(ObjectId id) {
        this.id = id;
        return this;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tickets=" + tickets +
                ", isAdmin=" + isAdmin +
                ", events=" + events +
                '}';
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public User(ObjectId id, String username, String password, List<Ticket> tickets, List<Event> events, boolean isAdmin){
        this.id = id;
        this.username = username;
        this.password = password;
        this.events = events;
        this.tickets = tickets;
        this.isAdmin = isAdmin;
    }

    public User(ObjectId id, String username, String password, boolean isAdmin, List<Ticket> tickets, List<Event> events){
        this.id = id;
        this.username = username;
        this.password = password;
        this.events = events;
        this.tickets = tickets;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password, List<Ticket> tickets, List<Event> events, boolean isAdmin){
        this.username = username;
        this.password = password;
        this.events = events;
        this.tickets = tickets;
        this.isAdmin = isAdmin;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User() {

    }

}
