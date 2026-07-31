package com.example.taskmanager.domain;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Optional;
@Getter
public class Task {
    private TaskId id;
    private String title;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description) {
        Assert.notNull(title, "Title must not be null");
        Assert.notNull(description, "Description must not be null");
        this.id = new TaskId();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

    public void update(String title, Optional<String> description, TaskStatus status) {
        Assert.notNull(title, "Title must not be null");
        Assert.notNull(description, "Description must not be null");
        Assert.notNull(status, "Status must not be null");
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
