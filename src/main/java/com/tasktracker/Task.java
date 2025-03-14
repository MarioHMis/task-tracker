package com.tasktracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    // Attributes
    private int id;
    private String description;
    private String status;
    private String createdAt; // Store as String
    private String updatedAt; // Store as String

    // Constructor
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo"; // Initial status
        this.createdAt = LocalDateTime.now().toString(); // Store as String
        this.updatedAt = this.createdAt; // Initially same as createdAt
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return LocalDateTime.parse(createdAt); // Convert String to LocalDateTime
    }

    public LocalDateTime getUpdatedAt() {
        return LocalDateTime.parse(updatedAt); // Convert String to LocalDateTime
    }

    // Setters
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now().toString(); // Update the timestamp as String
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now().toString(); // Update the timestamp as String
    }

    // toString method
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}