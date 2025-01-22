package com.mongodb.starter.model;

import com.mongodb.starter.bdd.MongoInterface;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class Ticket implements MongoInterface , Serializable {
    ObjectId id;
    ObjectId eventId;
    ObjectId userId;

    boolean valid;

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

    public Ticket(ObjectId id, ObjectId eventId, ObjectId userId, boolean valid) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.valid = valid;
    }

    public static Ticket testTicket() {
        return new Ticket(new ObjectId("42069"));
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Ticket(ObjectId id) {
        this.id = id;
    }

    public Ticket() {
    }
    @Override
    public Document toDocument() {
        return new Document()
                .append("id", this.id);
    }

    @Override
    public Ticket toObject(Document d) {
        return null;
    }


}
