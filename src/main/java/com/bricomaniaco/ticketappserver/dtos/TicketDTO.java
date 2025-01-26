package com.bricomaniaco.ticketappserver.dtos;

import com.bricomaniaco.ticketappserver.model.Ticket;
import org.bson.types.ObjectId;

/**
 * Data Transfer Object for Ticket.
 */
public record TicketDTO(
        String id,
        String eventId,
        String userId,
        boolean valid
) {
    /**
     * Constructs a new TicketDTO from a Ticket.
     *
     * @param t the Ticket
     */
    public TicketDTO(Ticket t) {
        this(
                t.getId() == null ? new ObjectId().toHexString() : t.getId().toHexString(),
                t.getEventId() == null ? new ObjectId().toHexString() : t.getEventId().toHexString(),
                t.getUserId() == null ? new ObjectId().toHexString() : t.getUserId().toHexString(),
                t.isValid()
        );
    }

    /**
     * Converts this TicketDTO to a Ticket.
     *
     * @return the Ticket
     */
    public Ticket toTicket() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        ObjectId _eventId = eventId == null ? new ObjectId() : new ObjectId(eventId);
        ObjectId _userId = userId == null ? new ObjectId() : new ObjectId(userId);
        return new Ticket(_id, _eventId, _userId, valid);
    }
}