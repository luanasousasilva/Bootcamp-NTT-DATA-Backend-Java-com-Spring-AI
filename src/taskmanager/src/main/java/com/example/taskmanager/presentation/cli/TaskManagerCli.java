package com.example.taskmanager.presentation.cli;

import com.example.taskmanager.application.CreateTaskUseCase;
import com.example.taskmanager.application.DeleteTaskUseCase;
import com.example.taskmanager.application.GetTaskUseCase;
import com.example.taskmanager.application.ListTasksUseCase;
import com.example.taskmanager.application.TaskNotFoundException;
import com.example.taskmanager.application.UpdateTaskUseCase;
import com.example.taskmanager.application.input.CreateTaskInput;
import com.example.taskmanager.application.input.DeleteTaskInput;
import com.example.taskmanager.application.input.GetTaskInput;
import com.example.taskmanager.application.input.UpdateTaskInput;
import com.example.taskmanager.application.output.TaskOutput;
import com.example.taskmanager.domain.TaskStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
@Profile("cli")
public class TaskManagerCli implements CommandLineRunner {
    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final ListTasksUseCase listTasksUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    public TaskManagerCli(CreateTaskUseCase createTaskUseCase, GetTaskUseCase getTaskUseCase,
                          ListTasksUseCase listTasksUseCase, UpdateTaskUseCase updateTaskUseCase,
                          DeleteTaskUseCase deleteTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.listTasksUseCase = listTasksUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                if (!scanner.hasNextLine()) {
                    break;
                }
                String option = read(scanner, "Escolha uma opção: ");
                try {
                    switch (option) {
                        case "1" -> createTask(scanner);
                        case "2" -> listTasks();
                        case "3" -> getTaskById(scanner);
                        case "4" -> updateTask(scanner);
                        case "5" -> deleteTask(scanner);
                        case "0" -> running = false;
                        default -> System.out.println("Opção inválida.");
                    }
                } catch (TaskNotFoundException | IllegalArgumentException exception) {
                    System.out.println("Erro: " + exception.getMessage());
                }
                System.out.println();
            }
        }
        System.out.println("Até logo!");
    }

    private void createTask(Scanner scanner) {
        String title = readRequired(scanner, "Título: ");
        Optional<String> description = readOptional(scanner, "Descrição (opcional): ");
        printTask(createTaskUseCase.execute(new CreateTaskInput(title, description)));
    }

    private void listTasks() {
        var tasks = listTasksUseCase.execute();
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        tasks.forEach(this::printTask);
    }

    private void getTaskById(Scanner scanner) {
        String id = readRequired(scanner, "ID da tarefa: ");
        printTask(getTaskUseCase.execute(new GetTaskInput(id)));
    }

    private void updateTask(Scanner scanner) {
        String id = readRequired(scanner, "ID da tarefa: ");
        String title = readRequired(scanner, "Novo título: ");
        Optional<String> description = readOptional(scanner, "Nova descrição (opcional): ");
        TaskStatus status = readStatus(scanner);
        printTask(updateTaskUseCase.execute(new UpdateTaskInput(id, title, description, status)));
    }

    private void deleteTask(Scanner scanner) {
        String id = readRequired(scanner, "ID da tarefa: ");
        deleteTaskUseCase.execute(new DeleteTaskInput(id));
        System.out.println("Tarefa excluída com sucesso.");
    }

    private TaskStatus readStatus(Scanner scanner) {
        System.out.println("Status: 1-PENDING, 2-IN_PROGRESS, 3-COMPLETED");
        return switch (read(scanner, "Novo status: ")) {
            case "1" -> TaskStatus.PENDING;
            case "2" -> TaskStatus.IN_PROGRESS;
            case "3" -> TaskStatus.COMPLETED;
            default -> throw new IllegalArgumentException("Status inválido.");
        };
    }

    private String readRequired(Scanner scanner, String prompt) {
        String value = read(scanner, prompt);
        if (value.isBlank()) {
            throw new IllegalArgumentException("Este campo é obrigatório.");
        }
        return value;
    }

    private Optional<String> readOptional(Scanner scanner, String prompt) {
        return Optional.of(read(scanner, prompt)).filter(value -> !value.isBlank());
    }

    private String read(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void printMenu() {
        System.out.println("=== Gerenciador de Tarefas ===");
        System.out.println("1 - Criar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Buscar tarefa por ID");
        System.out.println("4 - Atualizar tarefa");
        System.out.println("5 - Excluir tarefa");
        System.out.println("0 - Sair");
    }

    private void printTask(TaskOutput task) {
        String description = task.description().orElse("Sem descrição");
        System.out.printf("ID: %s | Título: %s | Descrição: %s | Status: %s%n",
                task.id(), task.title(), description, task.status());
    }
}
