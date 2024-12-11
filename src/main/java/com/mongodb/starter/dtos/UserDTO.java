package com.mongodb.starter.dtos;

import com.mongodb.starter.model.User;
import com.mongodb.starter.models.PersonEntity;
import org.bson.types.ObjectId;

import java.util.List;

public record UserDTO(
        String id,
        String username,
        String password,
        String isAdmin,
        List<EventDTO> events,
        List<TicketDTO> tickets

) {

    public UserDTO(User u){
        this(
                u.getId() == null ? new ObjectId().toHexString() : u.getId().toHexString(),
                u.getUsername(),
                u.getPassword(),
                u.isAdmin ? "true" : "false",
                u.getEvents().stream().map(EventDTO::new).toList(),
                u.getTickets().stream().map(TicketDTO::new).toList()
        );
    }


    public User toUser() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new User(_id, username, password, isAdmin == "true",
                tickets.stream().map(TicketDTO::toTicket).toList(),
                events.stream().map(EventDTO::toEvent).toList());
    }
}
