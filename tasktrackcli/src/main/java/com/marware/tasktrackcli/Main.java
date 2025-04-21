package com.marware.tasktrackcli;

import com.marware.tasktrackcli.service.TaskService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
            System.out.println("\n--- Task Tracker Menu ---");
            System.out.println("1. Add a new task");
            System.out.println("2. Update a task");
            System.out.println("3. Delete a task");
            System.out.println("4. Mark a task as in progress");
            System.out.println("5. Mark a task as done");
            System.out.println("6. List all tasks");
            System.out.println("7. List tasks by status");
            System.out.println("8. Exit");
            System.out.print("Choose an option (1-8): ");

            // Read the user's choice
            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid option. Please enter a number.");
                continue;
            }

            // Execute the corresponding action
            switch (option) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskService.addTask(description);
                    break;

                case 2:
                    System.out.print("Enter task ID: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    taskService.updateTask(updateId, newDescription);
                    break;

                case 3:
                    System.out.print("Enter task ID: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    taskService.deleteTask(deleteId);
                    break;

                case 4:
                    System.out.print("Enter task ID: ");
                    int inProgressId = Integer.parseInt(scanner.nextLine());
                    taskService.markTaskInProgress(inProgressId);
                    break;

                case 5:
                    System.out.print("Enter task ID: ");
                    int doneId = Integer.parseInt(scanner.nextLine());
                    taskService.markTaskDone(doneId);
                    break;

                case 6:
                    taskService.listTasks(null);
                    break;

                case 7:
                    System.out.print("Enter status (todo, in-progress, done): ");
                    String status = scanner.nextLine();
                    taskService.listTasks(status);
                    break;

                case 8:
                    System.out.println("Exiting Task Tracker. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Error: Invalid option. Please choose a number between 1 and 8.");
                    break;
            }
        }
    }
}