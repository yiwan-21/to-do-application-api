package com.todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.todo.todo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
