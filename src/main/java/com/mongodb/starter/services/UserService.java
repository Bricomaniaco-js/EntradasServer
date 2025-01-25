package com.mongodb.starter.services;

import com.mongodb.starter.dtos.EventDTO;
import com.mongodb.starter.dtos.TicketDTO;
import com.mongodb.starter.dtos.UserDTO;
import com.mongodb.starter.dtos.UserDTO;
import com.mongodb.starter.model.User;
import com.mongodb.starter.repositories.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

 /*
    public UserService(){

     }
  */
    public UserDTO findOne(String id) {
        return new UserDTO(userRepository.findOne(id));
    }
    public UserDTO save(UserDTO userDTO) {
        return new UserDTO(userRepository.save(userDTO.toUser()));
    }


    public UserDTO update(UserDTO UserDTO) {
        return new UserDTO(userRepository.update(UserDTO.toUser()));
    }

    public List<TicketDTO> getUserTickets(UserDTO userDTO){return userRepository.getUserTickets(userDTO.toUser()).stream().map(TicketDTO::new).toList();}

    public List<EventDTO> getUserEvents(UserDTO userDTO){return userRepository.getUserEvents(userDTO.toUser()).stream().map(EventDTO::new).toList();}

    public UserDTO purchaseTicket(UserDTO userDTO, EventDTO eventDTO) {
        return new UserDTO(userRepository.purchaseTicket(userDTO.toUser(), eventDTO.toEvent()));
    }
    public TicketDTO validateTicket(UserDTO userDTO, TicketDTO ticketDTO){
        return userRepository.adminValidateTicket(userDTO.toUser(), ticketDTO.toTicket());
    }

    public TicketDTO adminValidateTicket(UserDTO userDTO, TicketDTO ticketDTO){
        return userRepository.adminValidateTicket(userDTO.toUser(), ticketDTO.toTicket());
    }





    
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    public long count() {
        return userRepository.count();
    }
    
    public long delete(String id) {
        return userRepository.delete(id);
    }

    
    public long update(List<UserDTO> userEntities) {
        return userRepository.update(userEntities.stream().map(UserDTO::toUser).toList());
    }


    public UserDTO findByLogin(String username, String password) {
        return new UserDTO(userRepository.findByLogin(username, password));
    }
}
