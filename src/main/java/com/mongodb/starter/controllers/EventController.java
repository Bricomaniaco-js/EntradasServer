package com.mongodb.starter.controllers;

import com.mongodb.starter.dtos.EventDTO;
import com.mongodb.starter.dtos.UserDTO;
import com.mongodb.starter.services.EventService;
import com.mongodb.starter.services.PersonService;
import com.mongodb.starter.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("getEvents")
    public List<EventDTO> userGetEvents(){
        return eventService.userFindAll();
    }

    @PostMapping("user/AddEvent")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDTO postEvent(@RequestBody EventDTO eventDTO) {
        return eventService.save(eventDTO);
    }

}
