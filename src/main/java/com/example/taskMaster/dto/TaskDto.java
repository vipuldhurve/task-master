package com.example.taskMaster.dto;

import com.example.taskMaster.entity.TaskStatus;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
}
