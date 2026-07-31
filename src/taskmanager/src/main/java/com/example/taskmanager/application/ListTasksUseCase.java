package com.example.taskmanager.application;

import com.example.taskmanager.application.output.TaskOutput;
import com.example.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTasksUseCase {
    private final TaskRepository repository;

    public ListTasksUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskOutput> execute() {
        return repository.findAll().stream()
                .map(TaskOutput::from)
                .toList();
    }
}
