package com.example.taskmanager.infrastructure.http;

import com.example.taskmanager.application.CreateTaskUseCase;
import com.example.taskmanager.application.DeleteTaskUseCase;
import com.example.taskmanager.application.GetTaskUseCase;
import com.example.taskmanager.application.ListTasksUseCase;
import com.example.taskmanager.application.UpdateTaskUseCase;
import com.example.taskmanager.application.input.DeleteTaskInput;
import com.example.taskmanager.application.input.GetTaskInput;
import com.example.taskmanager.application.output.TaskOutput;
import com.example.taskmanager.infrastructure.http.request.CreateTaskRequest;
import com.example.taskmanager.infrastructure.http.request.UpdateTaskRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final ListTasksUseCase listTasksUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase, GetTaskUseCase getTaskUseCase,
                          ListTasksUseCase listTasksUseCase, UpdateTaskUseCase updateTaskUseCase,
                          DeleteTaskUseCase deleteTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.listTasksUseCase = listTasksUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskOutput create(@RequestBody CreateTaskRequest request) {
        return createTaskUseCase.execute(request.toInput());
    }

    @GetMapping
    public List<TaskOutput> list() {
        return listTasksUseCase.execute();
    }

    @GetMapping("/{id}")
    public TaskOutput getById(@PathVariable String id) {
        return getTaskUseCase.execute(new GetTaskInput(id));
    }

    @PutMapping("/{id}")
    public TaskOutput update(@PathVariable String id, @RequestBody UpdateTaskRequest request) {
        return updateTaskUseCase.execute(request.toInput(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deleteTaskUseCase.execute(new DeleteTaskInput(id));
    }

}
