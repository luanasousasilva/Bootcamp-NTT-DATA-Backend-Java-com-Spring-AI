package com.example.taskmanager.infrastructure.http.request;

import com.example.taskmanager.application.input.CreateTaskInput;

import java.util.Optional;

public record CreateTaskRequest(String title, String description) {
    public CreateTaskInput toInput() {
        return new CreateTaskInput(title, optionalDescription());
    }

    private Optional<String> optionalDescription() {
        return Optional.ofNullable(description).filter(value -> !value.isBlank());
    }
}
