package com.mongodb.starter.services;

import com.mongodb.starter.dtos.EventDTO;
import com.mongodb.starter.model.Event;
import com.mongodb.starter.model.User;
import com.mongodb.starter.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//TOOD: al implementar el repositorio  desanotar @Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    //TODO: IMPLEMENTar
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream().map(EventDTO::new).toList();
    }


}
