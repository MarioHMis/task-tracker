package com.tasktracker;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task Tracker started!");
        // Create a TaskManager instance
        TaskManager taskManager = new TaskManager();

        // Add tasks
        taskManager.addTask("Buy groceries");
        taskManager.addTask("Finish the report");

        // List all tasks
        taskManager.listTasks(null);

        // Update a task
        taskManager.updateTask(1, "Buy groceries and cook dinner");

        // Mark a task as in progress
        taskManager.markTaskInProgress(1);

        // Mark a task as done
        taskManager.markTaskDone(2);

        // List tasks by status
        taskManager.listTasks("done");

        // Delete a task
        taskManager.deleteTask(1);

        // List all tasks again
        taskManager.listTasks(null);
    }
}
