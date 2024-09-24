package com.example.taskMaster.controller;

import com.example.taskMaster.dto.UserDto;
import com.example.taskMaster.entity.User;
import com.example.taskMaster.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.createUser(userDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> userDtoList = userService.getAllUsers();
        if (!userDtoList.isEmpty()) {
            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found in database", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/session-id")
    public String getSession(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
