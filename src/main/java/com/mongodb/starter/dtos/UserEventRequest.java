package com.mongodb.starter.dtos;

public class UserEventRequest {
    private UserDTO userDTO;
    private EventDTO eventDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }
}