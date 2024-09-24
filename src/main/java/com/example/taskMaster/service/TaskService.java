package com.example.taskMaster.service;

import com.example.taskMaster.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    TaskDto getTaskDetailsById(Long id);
}
