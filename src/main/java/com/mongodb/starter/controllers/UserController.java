package com.mongodb.starter.controllers;

import com.mongodb.starter.dtos.*;
import com.mongodb.starter.model.User;
import com.mongodb.starter.services.PersonService;
import com.mongodb.starter.services.UserService;
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

    @PostMapping("user/registerUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO postUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PostMapping("user/buyTicket")
    public UserDTO userBuyTicket(@RequestBody UserEventRequest userEventRequest){
        //solo comprobar el usuario y la contraseña como verificacion de identidad
        return userService.purchaseTicket(userEventRequest.getUserDTO(), userEventRequest.getEventDTO());
    }

    @PostMapping("user/validateTicket")
    public TicketDTO userValidateTicket(@RequestBody UserTicketRequest userTicketRequest){
        return userService.validateTicket(userTicketRequest.getUserDTO(), userTicketRequest.getTicketDTO());
    }
    @GetMapping("user/getUserTickets")
    public List<TicketDTO> getUserTickets(@RequestParam String id){
        UserDTO userDTO = userService.findOne(id);
        if (userDTO == null) return null;
        return userService.getUserTickets(userDTO);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDTO> getUser(@RequestParam String id){
        UserDTO userDTO = userService.findOne(id);
        if (userDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("user/findByLogin")
    public ResponseEntity<UserDTO> findByLogin(@RequestParam String username, @RequestParam String password){
        UserDTO foundUserDTO = userService.findByLogin(username, password);
        if (foundUserDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(foundUserDTO);
    }

    @GetMapping("user/getUsers")
    public List<UserDTO> getUsers(){
        return userService.findAll();
    }




    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}
