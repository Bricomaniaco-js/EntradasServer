package com.bricomaniaco.ticketappserver.dtos;

import com.bricomaniaco.ticketappserver.model.User;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Data Transfer Object for User.
 */
public record UserDTO(
        String id,
        String username,
        String password,
        List<TicketDTO> tickets,
        List<EventDTO> events,
        boolean isAdmin
) {
    /**
     * Constructs a new UserDTO from a User.
     *
     * @param u the User
     */
    public UserDTO(User u) {
        this(
                u.getId() == null ? new ObjectId().toHexString() : u.getId().toHexString(),
                u.getUsername(),
                u.getPassword(),
                u.getTickets().stream().map(TicketDTO::new).toList(),
                u.getEvents().stream().map(EventDTO::new).toList(),
                u.isAdmin()
        );
    }

    /**
     * Converts this UserDTO to a User.
     *
     * @return the User
     */
    public User toUser() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new User(_id, username, password, tickets.stream().map(TicketDTO::toTicket).toList(), events.stream().map(EventDTO::toEvent).toList(), isAdmin);
    }
}