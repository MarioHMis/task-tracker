package com.marware.tasktrackcli;

import com.marware.tasktrackcli.model.Status;
import com.marware.tasktrackcli.model.Task;
import com.marware.tasktrackcli.service.TaskService;

import java.util.List;
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
                case "2" -> taskService.printNumberedTasks();
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
        Status status = chooseStatusFromMenu(scanner);
        if (status == null) return;

        List<Task> filtered = taskService.listTasksByStatus(status);

        if (filtered.isEmpty()) {
            System.out.println("📭 No tasks with status: " + status);
            return;
        }

        System.out.println("📋 Tasks with status: " + status);
        int index = 1;
        for (Task task : filtered) {
            System.out.println(index + ". [" + task.getStatus() + "] " + task.getDescription());
            index++;
        }
    }


    private static void handleUpdateStatus(TaskService taskService, Scanner scanner) {
        List<Task> tasks = taskService.printNumberedTasks();

        if (tasks.isEmpty()) return;

        System.out.print("🔢 Enter the task number to update: ");
        try {
            int taskIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                System.out.println("❌ Invalid task number.");
                return;
            }

            Task task = tasks.get(taskIndex);

            Status newStatus = chooseStatusFromMenu(scanner);
            if (newStatus == null) return;

            taskService.updateStatus(task.getId(), newStatus);

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Please enter a valid number.");
        }
    }


    private static void handleDeleteTask(TaskService taskService, Scanner scanner) {
        List<Task> tasks = taskService.printNumberedTasks();

        if (tasks.isEmpty()) return;

        System.out.print("🗑️ Enter the task number to delete: ");
        try {
            int taskIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                System.out.println("❌ Invalid task number.");
                return;
            }

            Task task = tasks.get(taskIndex);
            taskService.deleteTask(task.getId());

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid input. Please enter a valid number.");
        }
    }

    private static Status chooseStatusFromMenu(Scanner scanner) {
        Status[] statuses = Status.values();

        System.out.println("📂 Select a status to filter:");
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i]);
        }

        System.out.print("👉 Choice: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice < 1 || choice > statuses.length) {
                System.out.println("❌ Invalid option. Returning to menu.");
                return null;
            }

            return statuses[choice - 1];

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid input. Please enter a number.");
            return null;
        }
    }

}
