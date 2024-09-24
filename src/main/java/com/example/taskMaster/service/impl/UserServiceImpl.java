package com.example.taskMaster.service.impl;

import com.example.taskMaster.dto.UserDto;
import com.example.taskMaster.entity.User;
import com.example.taskMaster.exception.UserAlreadyExistsException;
import com.example.taskMaster.repository.UserRepository;
import com.example.taskMaster.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private final ObjectMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User createUser(UserDto userDto){
//        Check if user already exists with same username
        User user = userRepository.findByUsername(userDto.getUsername());
        if(user == null){
//            If no user exists with given username, create new user
            user = mapToEntity(userDto);
            User savedUser = userRepository.save(user);
            System.out.println("CREATED NEW USER: " + savedUser.getUsername());
            return savedUser;
        } else {
//            If user with same username exist, throw exception
            throw new UserAlreadyExistsException("User already exists!");
        }
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> usersList = userRepository.findAll();
        List<UserDto> userDtoList = usersList.stream().map(this::mapToDto).toList();
        return userDtoList;
    }

    private User mapToEntity(UserDto userDto) {
        return mapper.convertValue(userDto, User.class);
    }

    private UserDto mapToDto(User user) {
        return mapper.convertValue(user, UserDto.class);
    }
}
