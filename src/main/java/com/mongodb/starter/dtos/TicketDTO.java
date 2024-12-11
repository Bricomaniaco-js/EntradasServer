package com.mongodb.starter.dtos;

import com.mongodb.starter.model.Ticket;
import org.bson.types.ObjectId;

public record TicketDTO(
        String id
){
    public TicketDTO(Ticket t){
        this(
            t.getId() == null ? new ObjectId().toHexString() : t.getId().toHexString()
        );
    }
    public Ticket toTicket(){
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new Ticket(_id);

    }


}
