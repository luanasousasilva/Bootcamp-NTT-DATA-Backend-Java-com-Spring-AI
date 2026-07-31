package com.example.taskmanager.application;

import com.example.taskmanager.application.input.GetTaskInput;
import com.example.taskmanager.application.output.TaskOutput;
import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskRepository;
import com.example.taskmanager.infrastructure.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetTaskUseCaseTest {
    private TaskRepository repository;
    private GetTaskUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
        useCase = new GetTaskUseCase(repository);
    }

    @Test
    void shouldReturnTaskById() {
        Task task = repository.save(new Task("Estudar Java", Optional.of("Capítulo 3")));

        TaskOutput output = useCase.execute(new GetTaskInput(task.getId().id().toString()));

        assertEquals(task.getId().id().toString(), output.id());
        assertEquals("Estudar Java", output.title());
        assertEquals(Optional.of("Capítulo 3"), output.description());
        assertEquals("PENDING", output.status());
    }

    @Test
    void shouldThrowWhenTaskDoesNotExist() {
        String id = UUID.randomUUID().toString();

        TaskNotFoundException exception = assertThrows(TaskNotFoundException.class,
                () -> useCase.execute(new GetTaskInput(id)));

        assertEquals("Task not found: " + id, exception.getMessage());
    }
}
