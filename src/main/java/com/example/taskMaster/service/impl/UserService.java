package com.example.taskMaster.service.impl;

import com.example.taskMaster.dto.UserDto;
import com.example.taskMaster.entity.Users;
import com.example.taskMaster.exception.UserAlreadyExistsException;
import com.example.taskMaster.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    private final ObjectMapper mapper;

    public UserService(UserRepository userRepository, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public Users createUser(UserDto userDto){
//        Check if user already exists with same username
        Users user = userRepository.findByUsername(userDto.getUsername());
        if(user == null){
//            If no user exists with given username, create new user
            user = mapToEntity(userDto);
            Users savedUser = userRepository.save(user);
            System.out.println("CREATED NEW USER: " + savedUser.getUsername());
            return savedUser;
        } else {
//            If user with same username exist, throw exception
            throw new UserAlreadyExistsException("User already exists!");
        }
    }

    public List<UserDto> getAllUsers(){
        List<Users> usersList = userRepository.findAll();
        List<UserDto> userDtoList = usersList.stream().map(this::mapToDto).toList();
        return userDtoList;
    }

    private Users mapToEntity(UserDto userDto) {
        return mapper.convertValue(userDto, Users.class);
    }

    private UserDto mapToDto(Users user) {
        return mapper.convertValue(user, UserDto.class);
    }
}
