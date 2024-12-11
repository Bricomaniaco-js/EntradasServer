package com.mongodb.starter.model;

import com.mongodb.starter.bdd.MongoInterface;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class Ticket implements MongoInterface , Serializable {
    ObjectId id;

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
