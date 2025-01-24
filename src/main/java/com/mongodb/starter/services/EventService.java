package com.mongodb.starter.services;

import com.mongodb.starter.dtos.EventDTO;
import com.mongodb.starter.dtos.UserDTO;
import com.mongodb.starter.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream().map(EventDTO::new).toList();
    }
    public List<EventDTO> userFindAll() {
        return eventRepository.userFindAll().stream().map(EventDTO::new).toList();
    }


    public EventDTO save(EventDTO userDTO) {
        return new EventDTO(eventRepository.save(userDTO.toEvent()));
    }
}
