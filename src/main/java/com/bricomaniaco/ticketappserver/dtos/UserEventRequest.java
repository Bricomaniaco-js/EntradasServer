package com.bricomaniaco.ticketappserver.dtos;
/**
 * Data Transfer Object for User and Event request.
 */
public record UserEventRequest(
        UserDTO userDTO,
        EventDTO eventDTO
) {
    /**
     * Constructs a new UserEventRequest.
     *
     * @param userDTO the user data transfer object
     * @param eventDTO the event data transfer object
     */
    public UserEventRequest {

    }
    public UserDTO getUserDTO() {
        return userDTO;
    }


    public EventDTO getEventDTO() {
        return eventDTO;
    }

}