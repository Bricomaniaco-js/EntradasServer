package com.bricomaniaco.ticketappserver.model;

import org.bson.types.ObjectId;

import java.io.Serializable;
/**
 * Represents a ticket.
 */
public class Ticket implements Serializable {
    /**
     * The unique identifier for the ticket.
     */
    ObjectId id;

    /**
     * The event ID associated with the ticket.
     */
    ObjectId eventId;

    /**
     * The user ID associated with the ticket.
     */
    ObjectId userId;

    /**
     * Indicates whether the ticket is valid.
     */
    boolean valid;
    /**
     * Constructs a new Ticket.
     *
     * @param id the ticket ID
     * @param eventId the event ID
     * @param userId the user ID
     * @param valid the validity of the ticket
     */
    public Ticket(ObjectId id, ObjectId eventId, ObjectId userId, boolean valid) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.valid = valid;
    }
    /**
     * Default constructor for Ticket.
     */
    public Ticket(ObjectId id) {
        this.id = id;
    }

    public Ticket() {
    }

    public static Ticket testTicket() {
        return new Ticket(new ObjectId("42069"));
    }

    public ObjectId getEventId() {
        return eventId;
    }

    public void setEventId(ObjectId eventId) {
        this.eventId = eventId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
