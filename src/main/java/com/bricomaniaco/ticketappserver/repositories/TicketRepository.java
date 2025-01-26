package com.bricomaniaco.ticketappserver.repositories;

import com.bricomaniaco.ticketappserver.model.Event;
import com.bricomaniaco.ticketappserver.model.Ticket;
import com.bricomaniaco.ticketappserver.model.User;

/**
 * Repository interface for managing Ticket entities.
 */
public interface TicketRepository {

    /**
     * Checks if a ticket exists in the repository.
     *
     * @param ticket the ticket to check
     * @return true if the ticket exists, false otherwise
     */
    boolean ticketExists(Ticket ticket);

    /**
     * Generates a new ticket for a user for a specific event.
     *
     * @param user the user for whom the ticket is generated
     * @param event the event for which the ticket is generated
     * @return the generated ticket
     */
    Ticket generateTicket(User user, Event event);

    /**
     * Validates an existing ticket.
     *
     * @param ticket the ticket to validate
     * @return the validated ticket
     */
    Ticket validateTicket(Ticket ticket);

    /**
     * Gets the event associated with a ticket.
     *
     * @param ticket the ticket
     * @return the event associated with the ticket
     */
    Event getTicketEvent(Ticket ticket);

    /**
     * Finds a ticket by its ID.
     *
     * @param hexString the ticket ID
     * @return the found ticket, null if not found
     */
    Ticket findTicket(String hexString);

    /**
     * updates a ticket
     * @param ticket ticket to update
     * @return the updated ticket
     */
    Ticket update(Ticket ticket);
}