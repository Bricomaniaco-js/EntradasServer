package com.mongodb.starter.services;

import com.mongodb.starter.dtos.UserDTO;
import com.mongodb.starter.model.User;
import com.mongodb.starter.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
/*
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

 */
    public UserService(){

    }
    public UserDTO findOne(String id) {
        return new UserDTO(new User("",""));
    }
}
