package com.example.taskmanager.application;

import com.example.taskmanager.application.output.TaskOutput;
import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskRepository;
import com.example.taskmanager.infrastructure.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListTasksUseCaseTest {
    private TaskRepository repository;
    private ListTasksUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
        useCase = new ListTasksUseCase(repository);
    }

    @Test
    void shouldReturnAllTasks() {
        Task firstTask = repository.save(new Task("Comprar café", Optional.empty()));
        Task secondTask = repository.save(new Task("Enviar relatório", Optional.of("Até sexta-feira")));

        var output = useCase.execute();

        assertEquals(2, output.size());
        assertTrue(output.stream().map(TaskOutput::id)
                .toList().containsAll(java.util.List.of(
                        firstTask.getId().id().toString(), secondTask.getId().id().toString())));
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoTasks() {
        assertTrue(useCase.execute().isEmpty());
    }
}
