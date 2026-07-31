package com.example.taskmanager.infrastructure.http;

import com.example.taskmanager.application.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiError> handleTaskNotFound(TaskNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleInvalidArgument(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ApiError(exception.getMessage()));
    }

    public record ApiError(String message) {
    }
}
