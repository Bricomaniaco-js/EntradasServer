package com.bricomaniaco.ticketappserver.repositories;

import com.bricomaniaco.ticketappserver.model.Event;
import com.bricomaniaco.ticketappserver.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Event entities.
 */
@Repository
public interface EventRepository {

    /**
     * Checks if an event exists in the repository.
     *
     * @param event the event to check
     * @return true if the event exists, false otherwise
     */
     boolean eventExists(Event event);
    /**
     * Updates an existing event in the repository.
     *
     * @param eventEntity the event to update
     * @return the updated event
     */
    Event update(Event eventEntity);

    Event update(String eventId, Event eventEntity);
    /**
     * Finds all events with the specified IDs.
     *
     * @param ids the list of event IDs
     * @return the list of events
     */
    List<Event> findAll(List<String> ids);
    /**
     * Finds all events in the repository.
     *
     * @return the list of all events
     */
    List<Event> findAll();
    /**
     * Finds all events for a user.
     *
     * @return the list of user events
     */
    List<Event> userFindAll();

    /**
     * Adds a ticket to an event.
     *
     * @param event the event to add the ticket to
     * @param t the ticket to add
     * @return true if the ticket was added, false otherwise
     */
    boolean addTicket(Event event, Ticket t);
    /**
     * Checks if an event has available tickets.
     *
     * @param event the event to check
     * @return true if the event has available tickets, false otherwise
     */
    boolean eventHasAvailableTickets(Event event);
    /**
     * Saves a new event to the repository.
     *
     * @param event the event to save
     * @return the saved event
     */
    Event save(Event event);
    /**
     * Finds a user event by ID.
     *
     * @param id the event ID
     * @return the found event
     */
    Event userFindOne(String id);

    Event addAdmin(String eventId, String adminName);
}
