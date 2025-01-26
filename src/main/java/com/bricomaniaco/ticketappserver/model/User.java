package com.bricomaniaco.ticketappserver.model;

import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Represents a user.
 */
public class User implements Serializable {

    /**
     * Indicates whether the user can validate tickets or not.
     */
    public boolean isAdmin;

    /**
     * The unique identifier for the user.
     */
    ObjectId id;

    /**
     * The username of the user.
     */
    String username;

    /**
     * The password of the user.
     */
    String password;

    /**
     * The list of tickets associated with the user.
     */
    List<Ticket> tickets;

    /**
     * The list of events associated with the user.
     */
    List<Event> events;

    /**
     * Constructs a new User.
     *
     * @param id the user ID
     * @param username the username
     * @param password the password
     * @param tickets the list of tickets associated with the user
     * @param events the list of events associated with the user
     * @param isAdmin the boolean that indicates if the user is an admin
     */

    public User(ObjectId id, String username, String password, List<Ticket> tickets, List<Event> events, boolean isAdmin){
        this.id = id;
        this.username = username;
        this.password = password;
        this.events = events;
        this.tickets = tickets;
        this.isAdmin = isAdmin;
    }

    public User() {

    }

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
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

}
