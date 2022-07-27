package com.todo.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.todo.todo.model.Task;
import com.todo.todo.repository.TaskRepository;

public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Transactional
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        taskRepository.findAll().forEach(tasks::add);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Task> getTaskByID(long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<Task> createTask(Task task) {
        Task newTask = taskRepository.save(new Task(task.getTitle(), task.getDescription()));
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Task> updateTask(long id, Task task) {
        Optional<Task> taskData = taskRepository.findById(id);
        if (taskData.isPresent()) {
            Task _task = taskData.get();
            _task.setTitle(task.getTitle());
            _task.setDescription(task.getDescription());
            _task.setCompleted(task.isCompleted());
            Task updatedTask = taskRepository.save(_task);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<Task> deleteTask(long id) {
        taskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
