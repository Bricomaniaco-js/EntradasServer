package com.bricomaniaco.ticketappserver.controllers;

import com.bricomaniaco.ticketappserver.dtos.EventDTO;
import com.bricomaniaco.ticketappserver.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Event entities.
 */
@RestController
@RequestMapping("/api/events")
public class EventController {
    private final static Logger LOGGER = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;

    /**
     * Constructs a new EventController.
     *
     * @param eventService the event service
     */
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Retrieves all user events.
     *
     * @return the list of user event DTOs
     */
    @GetMapping("getEvents")
    public List<EventDTO> userGetEvents(){
        return eventService.userFindAll();
    }

    /**
     * Retrieves a user event by ID.
     *
     * @param eventId the event ID
     * @return the found event DTO
     */
    @GetMapping("getEvent")
    public EventDTO userGetEvent(@RequestParam String eventId){
        return eventService.userFindEvent(eventId);
    }

    /**
     * Creates a new event.
     *
     * @param eventDTO the event DTO
     * @return the created event DTO
     */
    @PostMapping("user/AddEvent")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDTO postEvent(@RequestBody EventDTO eventDTO) {
        return eventService.save(eventDTO);
    }
}