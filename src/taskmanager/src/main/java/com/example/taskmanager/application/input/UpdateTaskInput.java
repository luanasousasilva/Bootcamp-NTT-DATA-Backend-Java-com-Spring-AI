package com.example.taskmanager.application.input;

import com.example.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskInput(String id, String title, Optional<String> description, TaskStatus status) {
}
