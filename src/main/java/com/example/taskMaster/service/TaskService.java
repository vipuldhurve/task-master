package com.example.taskMaster.service;

import com.example.taskMaster.dto.TaskDto;

import java.util.List;

public interface TaskService {

    public TaskDto createTask(TaskDto taskDto);

    public List<TaskDto> getAllTasks();

    public TaskDto getTaskDetailsById(Long id);
}
