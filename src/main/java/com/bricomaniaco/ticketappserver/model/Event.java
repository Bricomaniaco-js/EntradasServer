package com.bricomaniaco.ticketappserver.model;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

/**
 * Represents an event.
 */
public class Event implements Serializable {
    /**
     * The unique identifier for the event.
     */
    ObjectId id;

    /**
     * The name of the event.
     */
    String name;

    /**
     * A description of the event.
     */
    String description;

    /**
     * The list of tickets available for the event.
     */
    List<Ticket> tickets;

    /**
     * The list of images associated with the event.
     */
    List<String> images;

    /**
     * The capacity of the event.
     */
    int capacity;

    /**
     * The price of the tickets for the event.
     */
    float price;

    /**
     * The location where the event will be held.
     */
    String location;

    /**
     * The date of the event. In format "DD/MM/YYYY HH:MM"
     */
    String date;

    /**
     * Constructs a new Event.
     *
     * @param id the event ID
     * @param name the event name
     * @param description the event description
     * @param tickets the list of tickets for the event
     * @param images the list of images for the event
     * @param capacity the capacity of the event
     * @param price the price of the event
     * @param location the location of the event
     * @param date the date of the event
     */
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
    /**
     * Default constructor for Event.
     */
    public Event(){

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
