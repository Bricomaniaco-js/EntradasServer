package com.mongodb.starter.dtos;

import com.mongodb.starter.model.Ticket;
import org.bson.types.ObjectId;

public record TicketDTO(
        String id,
        String eventId,
        String userId,
        String valid

){
    public TicketDTO(Ticket t){
        this(
            t.getId() == null ? new ObjectId().toHexString() : t.getId().toHexString(),
            t.getEventId() == null ? null : t.getEventId().toHexString(),
            t.getUserId() == null ? null : t.getUserId().toHexString(),
            t.isValid() ? "true" : "false"
        );
    }
    public Ticket toTicket(){
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        ObjectId _eventId = eventId == null ? null : new ObjectId(eventId);
        ObjectId _userId = userId == null ? null : new ObjectId(userId);
        return new Ticket(_id, _eventId, _userId, valid.equals("true"));

    }



}
