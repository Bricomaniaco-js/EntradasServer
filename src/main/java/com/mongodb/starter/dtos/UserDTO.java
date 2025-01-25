package com.mongodb.starter.dtos;


import com.mongodb.starter.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public record UserDTO(
        String id,
        String username,
        String password,
        Boolean isAdmin,
        List<EventDTO> events,
        List<TicketDTO> tickets

) {

    public UserDTO(User u){
        this(
                u.getId() == null ? new ObjectId().toHexString() : u.getId().toHexString(),
                u.getUsername(),
                u.getPassword(),
                u.isAdmin,
                u.getEvents().stream().map(EventDTO::new).toList(),
                u.getTickets().stream().map(TicketDTO::new).toList()
        );
    }


    public User toUser() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new User(_id, username, password, isAdmin,
                tickets.stream().map(TicketDTO::toTicket).toList(),
                events.stream().map(EventDTO::toEvent).toList());
    }
}
