package com.mongodb.starter.repositories;

import com.mongodb.starter.model.Event;
import com.mongodb.starter.model.Ticket;
import com.mongodb.starter.models.PersonEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository {

     boolean eventExists(Event event);

    Event update(Event eventEntity);

    List<Event> findAll(List<String> ids);

    List<Event> findAll();

    List<Event> userFindAll();


    boolean addTicket(Event event, Ticket t);

    boolean eventHasAvailableTickets(Event event);

    Event save(Event event);
}
