package com.bricomaniaco.ticketappserver.dtos;

/**
 * Data Transfer Object for User and Ticket request.
 */
public record UserTicketRequest(
        UserDTO userDTO,
        TicketDTO ticketDTO
) {
    /**
     * Constructs a new UserTicketRequest.
     *
     * @param userDTO the user data transfer object
     * @param ticketDTO the ticket data transfer object
     */
    public UserTicketRequest {

    }

        public UserDTO getUserDTO() {
            return userDTO;
        }

        public TicketDTO getTicketDTO() {
            return ticketDTO;
        }
}
