package com.example.taskmanager.infrastructure.repository;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskId;
import com.example.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTaskRepository implements TaskRepository {
    private final Map<TaskId, Task> storage = new HashMap<>();

    @Override
    public Task save(Task task) {
        storage.put(task.getId(),task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Task> findById(TaskId id) {

        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(TaskId id) {
        storage.remove(id);

    }
}
