package com.example.taskMaster.service;

import com.example.taskMaster.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    List<UserDto> getAllUsers();

    String verify(UserDto userDto);
}
