package com.bricomaniaco.ticketappserver.services;

import com.bricomaniaco.ticketappserver.repositories.UserRepository;
import com.bricomaniaco.ticketappserver.dtos.EventDTO;
import com.bricomaniaco.ticketappserver.dtos.TicketDTO;
import com.bricomaniaco.ticketappserver.dtos.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing User entities.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new UserService.
     *
     * @param userRepository the user repository
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Finds a user by ID.
     *
     * @param id the user ID
     * @return the found user DTO
     */
    public UserDTO findOne(String id) {
        return new UserDTO(userRepository.findOne(id));
    }

    /**
     * Saves a new user.
     *
     * @param userDTO the user DTO
     * @return the saved user DTO
     */
    public UserDTO save(UserDTO userDTO) {
        return new UserDTO(userRepository.save(userDTO.toUser()));
    }

    /**
     * Updates an existing user.
     *
     * @param userDTO the user DTO
     * @return the updated user DTO
     */
    public UserDTO update(UserDTO userDTO) {
        return new UserDTO(userRepository.update(userDTO.toUser()));
    }

    /**
     * Gets the tickets of a user.
     *
     * @param userDTO the user DTO
     * @return the list of user ticket DTOs
     */
    public List<TicketDTO> getUserTickets(UserDTO userDTO) {
        return userRepository.getUserTickets(userDTO.toUser()).stream().map(TicketDTO::new).toList();
    }

    /**
     * Gets the events of a user.
     *
     * @param userDTO the user DTO
     * @return the list of user event DTOs
     */
    public List<EventDTO> getUserEvents(UserDTO userDTO) {
        return userRepository.getUserEvents(userDTO.toUser()).stream().map(EventDTO::new).toList();
    }

    /**
     * Purchases a ticket for a user for a specific event.
     *
     * @param userDTO the user DTO
     * @param eventDTO the event DTO
     * @return the user DTO with the purchased ticket
     */
    public UserDTO purchaseTicket(UserDTO userDTO, EventDTO eventDTO) {
        return new UserDTO(userRepository.purchaseTicket(userDTO.toUser(), eventDTO.toEvent()));
    }

    /**
     * Validates a ticket by an admin user.
     *
     * @param userDTO the admin user DTO
     * @param ticketDTO the ticket DTO
     * @return the validated ticket DTO
     */
    public TicketDTO validateTicket(UserDTO userDTO, TicketDTO ticketDTO) {
        return userRepository.adminValidateTicket(userDTO.toUser(), ticketDTO.toTicket());
    }

    /**
     * Finds all users.
     *
     * @return the list of all user DTOs
     */
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    /**
     * Counts the number of users.
     *
     * @return the number of users
     */
    public long count() {
        return userRepository.count();
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the user ID
     * @return the number of deleted users
     */
    public long delete(String id) {
        return userRepository.delete(id);
    }

    /**
     * Updates a list of users.
     *
     * @param userEntities the list of user DTOs
     * @return the number of updated users
     */
    public long update(List<UserDTO> userEntities) {
        return userRepository.update(userEntities.stream().map(UserDTO::toUser).toList());
    }

    /**
     * Finds a user by login credentials.
     *
     * @param username the username
     * @param password the password
     * @return the found user DTO
     */
    public UserDTO findByLogin(String username, String password) {
        return new UserDTO(userRepository.findByLogin(username, password));
    }
}