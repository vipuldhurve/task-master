package com.example.taskMaster.controller;

import com.example.taskMaster.dto.TaskDto;
import com.example.taskMaster.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        List<TaskDto> taskDtoList = taskService.getAllTasks();
        if (!taskDtoList.isEmpty()) {
            return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Tasks created", HttpStatus.NO_CONTENT);
        }
    }
}
