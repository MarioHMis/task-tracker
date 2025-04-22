package com.marware.tasktrackcli;

import com.marware.tasktrackcli.model.Status;
import com.marware.tasktrackcli.service.TaskService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("🌀 Welcome to TaskTrackCLI - by MarWare\n");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("👉 Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> handleAddTask(taskService, scanner);
                case "2" -> taskService.listAllTasks();
                case "3" -> handleFilterByStatus(taskService, scanner);
                case "4" -> handleUpdateStatus(taskService, scanner);
                case "5" -> handleDeleteTask(taskService, scanner);
                case "0" -> {
                    running = false;
                    System.out.println("👋 See you next time!");
                }
                default -> System.out.println("❌ Invalid option. Please try again.");
            }

            System.out.println("\n────────────────────────────────\n");
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("""
                📋 Task Manager Menu:
                1. ➕ Add a new task
                2. 📋 View all tasks
                3. 🔎 Filter tasks by status
                4. 🔁 Update task status
                5. 🗑️ Delete a task
                0. 🚪 Exit
                """);
    }

    private static void handleAddTask(TaskService taskService, Scanner scanner) {
        System.out.print("📝 Enter task description: ");
        String description = scanner.nextLine();
        taskService.addTask(description);
    }

    private static void handleFilterByStatus(TaskService taskService, Scanner scanner) {
        System.out.print("🔍 Enter status to filter (TODO, IN_PROGRESS, DONE): ");
        try {
            String input = scanner.nextLine().trim().toUpperCase();
            Status status = Status.valueOf(input);
            taskService.listTasksByStatus(status);
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Invalid status. Try: TODO, IN_PROGRESS, or DONE.");
        }
    }

    private static void handleUpdateStatus(TaskService taskService, Scanner scanner) {
        System.out.print("🔑 Enter task ID: ");
        String id = scanner.nextLine();
        System.out.print("✏️ Enter new status (TODO, IN_PROGRESS, DONE): ");
        try {
            String input = scanner.nextLine().trim().toUpperCase();
            Status newStatus = Status.valueOf(input);
            taskService.updateStatus(id, newStatus);
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Invalid status format.");
        }
    }

    private static void handleDeleteTask(TaskService taskService, Scanner scanner) {
        System.out.print("🗑️ Enter task ID to delete: ");
        String id = scanner.nextLine();
        taskService.deleteTask(id);
    }
}
