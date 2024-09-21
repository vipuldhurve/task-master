package com.example.taskMaster.repository;

import com.example.taskMaster.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
