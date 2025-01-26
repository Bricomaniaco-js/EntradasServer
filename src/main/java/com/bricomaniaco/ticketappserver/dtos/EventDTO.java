package com.bricomaniaco.ticketappserver.dtos;

import com.bricomaniaco.ticketappserver.model.Event;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Data Transfer Object for Event.
 */
public record EventDTO(
        String id,
        String name,
        String description,
        List<TicketDTO>tickets,
        List<String> images,
        int capacity,
        float price,
        String location,
        String date


) {
    /**
     * Constructs a new EventDTO from an Event.
     *
     * @param e the Event
     */
    public EventDTO(Event e){
        this(
                e.getId() == null ? new ObjectId().toHexString() : e.getId().toHexString(),
                e.getName(),
                e.getDescription(),
                e.getTickets().stream().map(TicketDTO::new).toList(),
                e.getImages(),
                e.getCapacity(),
                e.getPrice(),
                e.getLocation(),
                e.getDate()
        );

    }
    /**
     * Converts this EventDTO to an Event.
     *
     * @return the Event
     */
    public Event toEvent() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new Event(_id,
                name,
                description,
                tickets.stream().map(TicketDTO::toTicket).toList(),
                images,
                capacity,
                price,
                location,
                date);
    }
}
