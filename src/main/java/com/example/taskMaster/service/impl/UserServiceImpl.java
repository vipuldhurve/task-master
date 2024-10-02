package com.example.taskMaster.service.impl;

import com.example.taskMaster.dto.UserDto;
import com.example.taskMaster.entity.User;
import com.example.taskMaster.exception.UserAlreadyExistsException;
import com.example.taskMaster.repository.UserRepository;
import com.example.taskMaster.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private AuthenticationManager authManager;

    private UserRepository userRepository;

    private JwtService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ObjectMapper mapper;

    @Autowired
    public UserServiceImpl(AuthenticationManager authManager, UserRepository userRepository, ObjectMapper mapper, JwtService jwtService) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        this.jwtService = jwtService;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        // Check if user already exists with same username
        User user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
            // If no user exists with given username, create new user
            user = mapToEntity(userDto);
            User savedUser = userRepository.save(user);
            UserDto savedUserDto = mapToDto(savedUser);
            System.out.println("CREATED NEW USER: " + savedUserDto.getUsername());
            return savedUserDto;
        } else {
            // If user with same username exist, throw exception
            throw new UserAlreadyExistsException(user.getUsername());
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        List<UserDto> userDtoList = usersList.stream().map(this::mapToDto).toList();
        return userDtoList;
    }

    @Override
    public String verify(UserDto userDto) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                );

        try {
            Authentication userAuth = authManager.authenticate(authToken);
            // If user is verified generate token
            if (userAuth.isAuthenticated())
                return jwtService.generateToken(userDto.getUsername());
        } catch (Exception e) {
            return "Failure! " + e.getMessage();
        }
        return "Unknown Error!";
    }

    private User mapToEntity(UserDto userDto) {
        User user = mapper.convertValue(userDto, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return user;
    }

    private UserDto mapToDto(User user) {
        return mapper.convertValue(user, UserDto.class);
    }
}
