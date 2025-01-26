package com.bricomaniaco.ticketappserver.repositories;

import com.bricomaniaco.ticketappserver.dtos.TicketDTO;
import com.bricomaniaco.ticketappserver.model.Event;
import com.bricomaniaco.ticketappserver.model.Ticket;
import com.bricomaniaco.ticketappserver.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing User entities.
 */
@Repository
public interface UserRepository {
    /**
     * Saves a new user to the repository.
     *
     * @param userEntity the user to save
     * @return the saved user
     */
    User save(User userEntity);
    /**
     * Saves a list of users to the repository.
     *
     * @param userEntities the list of users to save
     * @return the list of saved users
     */
    List<User> saveAll(List<User> userEntities);
    /**
     * Finds all users in the repository.
     *
     * @return the list of all users
     */
    List<User> findAll();
    /**
     * Finds all users with the specified IDs.
     *
     * @param ids the list of user IDs
     * @return the list of users
     */
    List<User> findAll(List<String> ids);
    /**
     * Finds a user by ID.
     *
     * @param id the user ID
     * @return the found user
     */
    User findOne(String id);
    /**
     * Counts the number of users in the repository.
     *
     * @return the number of users
     */
    long count();
    /**
     * Deletes a user by ID.
     *
     * @param id the user ID
     * @return the number of deleted users
     */
    long delete(String id);
    /**
     * Updates an existing user in the repository.
     *
     * @param userEntity the user to update
     * @return the updated user
     */
    User update(User userEntity);
    /**
     * Updates a list of users in the repository.
     *
     * @param userEntities the list of users to update
     * @return the number of updated users
     */
    long update(List<User> userEntities);
    /**
     * Purchases a ticket for a user for a specific event.
     *
     * @param user the user purchasing the ticket
     * @param event the event for which the ticket is purchased
     * @return the user with the purchased ticket
     */
    User purchaseTicket(User user, Event event);
    /**
     * Gets the tickets of a user.
     *
     * @param user the user whose tickets are to be retrieved
     * @return the list of user tickets
     */
    List<Ticket> getUserTickets(User user);
    /**
     * Gets the events of a user.
     *
     * @param user the user whose events are to be retrieved
     * @return the list of user events
     */
    List<Event> getUserEvents(User user);


    /**
     * Validates a ticket by an admin user.
     *
     * @param user the admin user validating the ticket
     * @param ticket the ticket to validate
     * @return the validated ticket DTO
     */
    TicketDTO adminValidateTicket(User user, Ticket ticket);

    /**
     * Finds a user by login credentials.
     * @param username the username
     * @param password the password
     * @return the found user, null if not found
     */
    User findByLogin(String username, String password);


    /**
     * Adds a ticket to a user.
     *
     * @param user the user to add the ticket to
     * @param t the ticket to add
     * @return true if the ticket was added, false otherwise
     */
    boolean addTicket(User user, Ticket t);
    /**
     * Checks if a user exists in the repository.
     *
     * @param user the user to check
     * @return true if the user exists, false otherwise
     */
    boolean userExists(User user);
}
