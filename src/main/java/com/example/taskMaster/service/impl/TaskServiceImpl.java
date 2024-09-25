package com.example.taskMaster.service.impl;

import com.example.taskMaster.dto.TaskDto;
import com.example.taskMaster.entity.Task;
import com.example.taskMaster.repository.TaskRepository;
import com.example.taskMaster.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private final ObjectMapper mapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ObjectMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = mapToEntity(taskDto);
        Task savedtask = taskRepository.save(task);
        return mapToDto(savedtask);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskDto> taskDtoList = taskList.stream().map(this::mapToDto).toList();
        return taskDtoList;
    }

    @Override
    public TaskDto getTaskDetailsById(Long id) {
        return null;
    }

    private Task mapToEntity(TaskDto taskDto) {
        return mapper.convertValue(taskDto, Task.class);
    }

    private TaskDto mapToDto(Task task) {
        return mapper.convertValue(task, TaskDto.class);
    }
}
