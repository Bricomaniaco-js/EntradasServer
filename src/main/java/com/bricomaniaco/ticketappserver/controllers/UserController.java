package com.bricomaniaco.ticketappserver.controllers;

import com.bricomaniaco.ticketappserver.dtos.TicketDTO;
import com.bricomaniaco.ticketappserver.dtos.UserDTO;
import com.bricomaniaco.ticketappserver.dtos.UserEventRequest;
import com.bricomaniaco.ticketappserver.dtos.UserTicketRequest;
import com.bricomaniaco.ticketappserver.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param userDTO the user data transfer object
     * @return the registered user
     */
    @PostMapping("user/registerUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO postUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }


    /**
     * Allows a user to buy a ticket.
     *
     * @param userEventRequest the user event request
     * @return the updated user with the purchased ticket
     */
    @PostMapping("user/buyTicket")
    public UserDTO userBuyTicket(@RequestBody UserEventRequest userEventRequest){
        //solo comprobar el usuario y la contraseña como verificacion de identidad
        return userService.purchaseTicket(userEventRequest.getUserDTO(), userEventRequest.getEventDTO());
    }


    /**
     * Allows an admin to validate a ticket.
     *
     * @param userTicketRequest contains the admin performing the validation and the ticket to be validated
     * @return the validated ticket
     */
    @PostMapping("user/validateTicket")
    public TicketDTO userValidateTicket(@RequestBody UserTicketRequest userTicketRequest){
        return userService.validateTicket(userTicketRequest.getUserDTO(), userTicketRequest.getTicketDTO());
    }

    /**
     * Retrieves the tickets of a user.
     *
     * @param id the user ID
     * @return the list of tickets
     */
    @GetMapping("user/getUserTickets")
    public List<TicketDTO> getUserTickets(@RequestParam String id){
        UserDTO userDTO = userService.findOne(id);
        if (userDTO == null) return null;
        return userService.getUserTickets(userDTO);
    }


    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return the user
     */
    /*
    @GetMapping("user/id")
    public ResponseEntity<UserDTO> getUser(@RequestParam String id){
        UserDTO userDTO = userService.findOne(id);
        if (userDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(userDTO);
    }
    */

    /**
     * Retrieves a user by login credentials.
     *
     * @param username the username
     * @param password the password
     * @return the user
     */
    @GetMapping("user/findByLogin")
    public ResponseEntity<UserDTO> findByLogin(@RequestParam String username, @RequestParam String password){
        UserDTO foundUserDTO = userService.findByLogin(username, password);
        if (foundUserDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(foundUserDTO);
    }

    /**
     * Retrieves all users.
     *
     * @return the list of users
     */
    /*
    @GetMapping("user/getUsers")
    public List<UserDTO> getUsers(){
        return userService.findAll();
    }
    */


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}
