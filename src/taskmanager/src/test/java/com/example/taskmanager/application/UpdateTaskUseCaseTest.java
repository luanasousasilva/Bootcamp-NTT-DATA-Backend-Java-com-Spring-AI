package com.example.taskmanager.application;

import com.example.taskmanager.application.input.UpdateTaskInput;
import com.example.taskmanager.application.output.TaskOutput;
import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskRepository;
import com.example.taskmanager.domain.TaskStatus;
import com.example.taskmanager.infrastructure.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdateTaskUseCaseTest {
    private TaskRepository repository;
    private UpdateTaskUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
        useCase = new UpdateTaskUseCase(repository);
    }

    @Test
    void shouldUpdateTask() {
        Task task = repository.save(new Task("Estudar Java", Optional.empty()));
        UpdateTaskInput input = new UpdateTaskInput(task.getId().id().toString(), "Estudar Spring",
                Optional.of("Criar uma API"), TaskStatus.IN_PROGRESS);

        TaskOutput output = useCase.execute(input);

        assertEquals("Estudar Spring", output.title());
        assertEquals(Optional.of("Criar uma API"), output.description());
        assertEquals("IN_PROGRESS", output.status());
        assertEquals("Estudar Spring", repository.findById(task.getId()).orElseThrow().getTitle());
    }

    @Test
    void shouldThrowWhenUpdatingTaskThatDoesNotExist() {
        String id = UUID.randomUUID().toString();
        UpdateTaskInput input = new UpdateTaskInput(id, "Título", Optional.empty(), TaskStatus.PENDING);

        assertThrows(TaskNotFoundException.class, () -> useCase.execute(input));
    }
}
