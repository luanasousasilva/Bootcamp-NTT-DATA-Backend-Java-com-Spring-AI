package com.example.taskmanager.infrastructure.repository;

import com.example.taskmanager.domain.TaskRepository;
import com.example.taskmanager.domain.TaskRepositoryTest;

class InMemoryTaskRepositoryTest extends TaskRepositoryTest {

    @Override
    protected TaskRepository createRepository() {
        return new InMemoryTaskRepository();
    }
}
