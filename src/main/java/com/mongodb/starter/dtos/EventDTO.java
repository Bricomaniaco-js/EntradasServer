package com.mongodb.starter.dtos;

import com.mongodb.starter.model.Event;
import com.mongodb.starter.model.Ticket;
import com.mongodb.starter.model.User;
import org.bson.types.ObjectId;

import java.util.List;

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
