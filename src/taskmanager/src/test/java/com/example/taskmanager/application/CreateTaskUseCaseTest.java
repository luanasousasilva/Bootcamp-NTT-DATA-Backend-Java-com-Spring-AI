package com.example.taskmanager.application;

import com.example.taskmanager.application.input.CreateTaskInput;
import com.example.taskmanager.application.output.TaskOutput;
import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskRepository;
import com.example.taskmanager.infrastructure.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CreateTaskUseCaseTest {
    private TaskRepository repository;
    private CreateTaskUseCase useCase;

    @BeforeEach
   void setUp() {
       repository = new InMemoryTaskRepository();
       useCase = new CreateTaskUseCase(repository);
    }

    @Test
    void shouldCreateAndSaveTask() {
        CreateTaskInput input = new CreateTaskInput("Comprar café", Optional.of("Grãos moídos"));

        TaskOutput output = useCase.execute(input);

        Task savedTask = repository.findAll().getFirst();
        assertEquals("Comprar café", savedTask.getTitle());
        assertEquals(Optional.of("Grãos moídos"), savedTask.getDescription());
        assertEquals(savedTask.getId().id().toString(), output.id());
        assertEquals("Comprar café", output.title());
        assertEquals(Optional.of("Grãos moídos"), output.description());
        assertEquals("PENDING", output.status());
        assertSame(savedTask, repository.findById(savedTask.getId()).orElseThrow());
    }
}
