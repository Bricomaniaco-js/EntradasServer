package com.mongodb.starter.controllers;

import com.mongodb.starter.dtos.PersonDTO;
import com.mongodb.starter.dtos.UserDTO;
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
    private PersonService personService;
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO postPerson(@RequestBody PersonDTO PersonDTO) {
        return personService.save(PersonDTO);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDTO> getUser(@RequestBody String id){
        UserDTO userDTO = userService.findOne(id);
        if (userDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(userDTO);
    }


    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PersonDTO> postPersons(@RequestBody List<PersonDTO> personEntities) {
        return personService.saveAll(personEntities);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}
