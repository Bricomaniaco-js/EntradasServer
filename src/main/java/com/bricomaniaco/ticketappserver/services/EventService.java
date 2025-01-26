package com.bricomaniaco.ticketappserver.services;

import com.bricomaniaco.ticketappserver.dtos.EventDTO;
import com.bricomaniaco.ticketappserver.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Event entities.
 */
@Service
public class EventService {

    private final EventRepository eventRepository;

    /**
     * Constructs a new EventService.
     *
     * @param eventRepository the event repository
     */
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Finds all events.
     *
     * @return the list of all event DTOs
     */
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream().map(EventDTO::new).toList();
    }

    /**
     * Finds all user events.
     *
     * @return the list of user event DTOs
     */
    public List<EventDTO> userFindAll() {
        return eventRepository.userFindAll().stream().map(EventDTO::new).toList();
    }

    /**
     * Finds a user event by ID.
     *
     * @param id the event ID
     * @return the found event DTO
     */
    public EventDTO userFindEvent(String id) {
        return new EventDTO(eventRepository.userFindOne(id));
    }

    /**
     * Saves a new event.
     *
     * @param eventDTO the event DTO
     * @return the saved event DTO
     */
    public EventDTO save(EventDTO eventDTO) {
        return new EventDTO(eventRepository.save(eventDTO.toEvent()));
    }
}