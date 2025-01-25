package com.mongodb.starter.repositories;

import com.mongodb.starter.dtos.TicketDTO;
import com.mongodb.starter.dtos.UserDTO;
import com.mongodb.starter.model.Event;
import com.mongodb.starter.model.Ticket;
import com.mongodb.starter.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository {

    User save(User userEntity);

    List<User> saveAll(List<User> userEntities);

    List<User> findAll();

    List<User> findAll(List<String> ids);

    User findOne(String id);

    long count();

    long delete(String id);

    User update(User userEntity);

    long update(List<User> userEntities);


    User purchaseTicket(User user, Event event);

    List<Ticket> getUserTickets(User user);

    List<Event> getUserEvents(User user);


    //todo
    TicketDTO adminValidateTicket(User user, Ticket ticket);

    User findByLogin(String username, String password);


    boolean addTicket(User user, Ticket t);
}
