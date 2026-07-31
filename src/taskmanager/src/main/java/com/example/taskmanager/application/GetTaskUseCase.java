package com.example.taskmanager.application;

import com.example.taskmanager.application.input.GetTaskInput;
import com.example.taskmanager.application.output.TaskOutput;
import com.example.taskmanager.domain.TaskId;
import com.example.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetTaskUseCase {
    private final TaskRepository repository;

    public GetTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(GetTaskInput input) {
        var taskId = new TaskId(UUID.fromString(input.id()));
        var task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(input.id()));
        return TaskOutput.from(task);
    }
}
