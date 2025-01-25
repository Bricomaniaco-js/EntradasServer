package com.mongodb.starter.model;

import com.mongodb.starter.bdd.MongoInterface;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {
    ObjectId id;
    String name;
    String description;
    List<Ticket> tickets;
    List<String> images;
    int capacity;
    float price;

    public Event(ObjectId id, String name, String description, List<Ticket> tickets, List<String> images, int capacity, float price, String location, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tickets = tickets;
        this.images = images;
        this.capacity = capacity;
        this.price = price;
        this.location = location;
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String location;
    String date;

    public Event(ObjectId id, String name, String description, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tickets = tickets;
    }

    public Event(ObjectId id, String name, String description, List<Ticket> tickets, List<String> images, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tickets = tickets;
        this.images = images;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Event(ObjectId id, String name, String description, List<Ticket> tickets, List<String> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tickets = tickets;
        this.images = images;
    }
    public Event(){

    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


}
