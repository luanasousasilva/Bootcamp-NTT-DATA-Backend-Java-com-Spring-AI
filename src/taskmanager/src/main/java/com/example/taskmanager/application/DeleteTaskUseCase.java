package com.example.taskmanager.application;

import com.example.taskmanager.application.input.DeleteTaskInput;
import com.example.taskmanager.domain.TaskId;
import com.example.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteTaskUseCase {
    private final TaskRepository repository;

    public DeleteTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(DeleteTaskInput input) {
        var taskId = new TaskId(UUID.fromString(input.id()));
        if (repository.findById(taskId).isEmpty()) {
            throw new TaskNotFoundException(input.id());
        }
        repository.delete(taskId);
    }
}
