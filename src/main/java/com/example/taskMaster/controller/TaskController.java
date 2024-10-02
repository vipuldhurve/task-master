package com.example.taskMaster.controller;

import com.example.taskMaster.dto.TaskDto;
import com.example.taskMaster.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
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
        if (!taskDtoList.isEmpty()) return ResponseEntity.ok(taskDtoList);
        else return ResponseEntity.ok("No tasks created!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskDetailsById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(taskService.getTaskDetailsById(id));
    }
}
