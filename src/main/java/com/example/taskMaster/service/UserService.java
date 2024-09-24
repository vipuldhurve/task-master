package com.example.taskMaster.service;

import com.example.taskMaster.dto.UserDto;
import com.example.taskMaster.entity.User;
import com.example.taskMaster.exception.UserAlreadyExistsException;
import com.example.taskMaster.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);

    List<UserDto> getAllUsers();
}
