package com.example.taskmanager.infrastructure.http.request;

import com.example.taskmanager.application.input.UpdateTaskInput;
import com.example.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskRequest(String title, String description, TaskStatus status) {
    public UpdateTaskInput toInput(String id) {
        return new UpdateTaskInput(id, title, optionalDescription(), status);
    }

    private Optional<String> optionalDescription() {
        return Optional.ofNullable(description).filter(value -> !value.isBlank());
    }
}
