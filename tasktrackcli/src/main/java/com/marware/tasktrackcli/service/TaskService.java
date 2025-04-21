package com.marware.tasktrackcli.service;

import com.marware.tasktrackcli.util.JsonUtil;
import com.marware.tasktrackcli.model.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    // Attributes
    private Map<Integer, Task> tasks; // Stores tasks by their ID
    private int nextId; // Counter for generating unique IDs
    private final JsonUtil jsonUtil; // Handles file operations

    // Constructor
    public TaskService() {
        this.jsonUtil = new JsonUtil();
        this.tasks = jsonUtil.readTasksFromFile(); // Load tasks from file
        if (this.tasks == null) {
            this.tasks = new HashMap<>(); // Initialize an empty map if no tasks exist
        }
        this.nextId = tasks.isEmpty() ? 1 : tasks.keySet().stream().max(Integer::compare).get() + 1; // Set nextId
    }

    // Method to add a new task
    public void addTask(String description) {
        Task task = new Task(nextId, description); // Create a new task
        tasks.put(nextId, task); // Add the task to the map
        nextId++; // Increment the ID counter
        saveTasks(); // Save tasks to file
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }

    // Method to update a task's description
    public void updateTask(int id, String newDescription) {
        Task task = tasks.get(id); // Get the task by ID
        if (task != null) {
            task.setDescription(newDescription); // Update the description
            saveTasks(); // Save tasks to file
            System.out.println("Task updated successfully (ID: " + id + ")");
        } else {
            System.out.println("Task not found (ID: " + id + ")");
        }
    }

    // Method to delete a task
    public void deleteTask(int id) {
        Task task = tasks.remove(id); // Remove the task by ID
        if (task != null) {
            saveTasks(); // Save tasks to file
            System.out.println("Task deleted successfully (ID: " + id + ")");
        } else {
            System.out.println("Task not found (ID: " + id + ")");
        }
    }

    // Method to mark a task as in progress
    public void markTaskInProgress(int id) {
        Task task = tasks.get(id); // Get the task by ID
        if (task != null) {
            task.setStatus("in-progress"); // Update the status
            saveTasks(); // Save tasks to file
            System.out.println("Task marked as in progress (ID: " + id + ")");
        } else {
            System.out.println("Task not found (ID: " + id + ")");
        }
    }

    // Method to mark a task as done
    public void markTaskDone(int id) {
        Task task = tasks.get(id); // Get the task by ID
        if (task != null) {
            task.setStatus("done"); // Update the status
            saveTasks(); // Save tasks to file
            System.out.println("Task marked as done (ID: " + id + ")");
        } else {
            System.out.println("Task not found (ID: " + id + ")");
        }
    }

    // Method to list tasks (all or filtered by status)
    public void listTasks(String status) {
        System.out.println("Tasks:");
        for (Task task : tasks.values()) {
            if (status == null || task.getStatus().equals(status)) {
                System.out.println(task);
            }
        }
    }

    // Method to save tasks to the JSON file
    private void saveTasks() {
        jsonUtil.writeTasksToFile(tasks);
    }
}