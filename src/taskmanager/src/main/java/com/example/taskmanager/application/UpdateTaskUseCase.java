package com.example.taskmanager.application;

import com.example.taskmanager.application.input.UpdateTaskInput;
import com.example.taskmanager.application.output.TaskOutput;
import com.example.taskmanager.domain.TaskId;
import com.example.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateTaskUseCase {
    private final TaskRepository repository;

    public UpdateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(UpdateTaskInput input) {
        var taskId = new TaskId(UUID.fromString(input.id()));
        var task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(input.id()));

        task.update(input.title(), input.description(), input.status());
        return TaskOutput.from(repository.save(task));
    }
}
