package com.example.taskmanager.application;

import com.example.taskmanager.application.input.DeleteTaskInput;
import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskRepository;
import com.example.taskmanager.infrastructure.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteTaskUseCaseTest {
    private TaskRepository repository;
    private DeleteTaskUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
        useCase = new DeleteTaskUseCase(repository);
    }

    @Test
    void shouldDeleteTask() {
        Task task = repository.save(new Task("Pagar conta", Optional.empty()));

        useCase.execute(new DeleteTaskInput(task.getId().id().toString()));

        assertTrue(repository.findById(task.getId()).isEmpty());
    }

    @Test
    void shouldThrowWhenDeletingTaskThatDoesNotExist() {
        assertThrows(TaskNotFoundException.class,
                () -> useCase.execute(new DeleteTaskInput(UUID.randomUUID().toString())));
    }
}
