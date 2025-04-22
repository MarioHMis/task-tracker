package com.marware.tasktrackcli.service;

import com.marware.tasktrackcli.model.Task;
import com.marware.tasktrackcli.model.Status;
import com.marware.tasktrackcli.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {

    private final List<Task> tasks;

    public TaskService() {
        this.tasks = JsonUtil.loadTasks();
    }

    public void addTask(String description) {
        if (description == null || description.trim().isEmpty()) {
            System.out.println("⚠️ Task description cannot be empty.");
            return;
        }
        Task task = new Task(description);
        tasks.add(task);
        saveTasks();
        System.out.println("✅ Task added successfully.");
    }

    public void listAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("📭 No tasks available.");
            return;
        }
        System.out.println("\n📝 Task List:");
        tasks.forEach(task -> System.out.println(task));
    }

    public List<Task> listTasksByStatus(Status status) {
        List<Task> filtered = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus() == status) {
                filtered.add(task);
            }
        }
        return filtered;
    }


    public void updateStatus(String id, Status newStatus) {
        Optional<Task> taskOpt = findTaskById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setStatus(newStatus);
            saveTasks();
            System.out.println("🔁 Status updated to: " + newStatus);
        } else {
            System.out.println("❌ Task not found.");
        }
    }

    public void deleteTask(String id) {
        Optional<Task> taskOpt = findTaskById(id);
        if (taskOpt.isPresent()) {
            tasks.remove(taskOpt.get());
            saveTasks();
            System.out.println("🗑️ Task deleted.");
        } else {
            System.out.println("❌ Task not found.");
        }
    }

    private Optional<Task> findTaskById(String id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    private void saveTasks() {
        JsonUtil.saveTasks(tasks);
    }

    public List<Task> printNumberedTasks() {
        if (tasks.isEmpty()) {
            System.out.println("📭 No tasks available.");
            return new ArrayList<>();
        }

        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + ". [" + task.getStatus() + "] " + task.getDescription());
            index++;
        }
        return tasks;
    }



    //JunitTest
    public List<Task> getAllTasks() {
        return tasks;
    }
}


