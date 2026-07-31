package com.example.taskmanager.application;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String taskId) {
        super("Task not found: " + taskId);
    }
}
