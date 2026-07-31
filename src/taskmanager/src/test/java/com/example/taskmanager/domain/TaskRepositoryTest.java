package com.example.taskmanager.domain;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class TaskRepositoryTest {

    protected TaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = createRepository();
    }

    protected abstract TaskRepository createRepository();

    @Test
    void shouldSaveTaskAndReturnIt() {
        Task task = new Task("Comprar café", Optional.of("Grãos moídos"));

        Task savedTask = repository.save(task);

        assertSame(task, savedTask);
        assertSame(task, repository.findById(task.getId()).orElseThrow());
    }

    @Test
    void shouldReturnAllSavedTasks() {
        Task firstTask = new Task("Comprar café", Optional.empty());
        Task secondTask = new Task("Enviar relatório", Optional.of("Até sexta-feira"));

        repository.save(firstTask);
        repository.save(secondTask);

        assertEquals(2, repository.findAll().size());
        assertTrue(repository.findAll().containsAll(List.of(firstTask, secondTask)));
    }

    @Test
    void shouldReturnEmptyListWhenNoTaskWasSaved() {
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void shouldFindTaskById() {
        Task task = new Task("Estudar Java", Optional.empty());
        repository.save(task);

        Optional<Task> foundTask = repository.findById(task.getId());

        assertTrue(foundTask.isPresent());
        assertSame(task, foundTask.orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenTaskIdDoesNotExist() {
        Task unsavedTask = new Task("Tarefa inexistente", Optional.empty());

        Optional<Task> foundTask = repository.findById(unsavedTask.getId());

        assertTrue(foundTask.isEmpty());
    }

    @Test
    void shouldDeleteTaskById() {
        Task task = new Task("Pagar conta", Optional.empty());
        repository.save(task);

        repository.delete(task.getId());

        assertFalse(repository.findById(task.getId()).isPresent());
        assertTrue(repository.findAll().isEmpty());
    }
}
