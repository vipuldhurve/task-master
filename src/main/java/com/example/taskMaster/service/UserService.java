package com.example.taskMaster.service;

import com.example.taskMaster.dto.UserDto;
import com.example.taskMaster.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);

    List<UserDto> getAllUsers();
}
