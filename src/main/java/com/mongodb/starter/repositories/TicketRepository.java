package com.mongodb.starter.repositories;

import com.mongodb.starter.model.Event;
import com.mongodb.starter.model.Ticket;
import com.mongodb.starter.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository {

    boolean ticketExists(Ticket ticket);
    Ticket generateTicket(User user, Event event);

    Ticket validateTicket(Ticket ticket);

    Event getTicketEvent(Ticket ticket);

    Ticket findTicket(String hexString);
}
