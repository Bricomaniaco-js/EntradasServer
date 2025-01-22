package com.mongodb.starter.bdd;

import org.bson.Document;

public interface MongoInterface {
    Document toDocument();
    Object toObject(Document d);
}
