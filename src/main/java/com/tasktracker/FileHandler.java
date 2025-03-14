package com.tasktracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    private static final String FILE_PATH = "tasks.json"; // Path to the JSON file
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Gson instance with pretty printing

    // Method to read tasks from the JSON file
    public Map<Integer, Task> readTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No existing tasks file found. A new one will be created.");
            return new HashMap<>(); // Return an empty map if the file doesn't exist
        }

        try (FileReader reader = new FileReader(file)) {
            Type taskMapType = new TypeToken<Map<Integer, Task>>() {}.getType();
            Map<Integer, Task> tasks = gson.fromJson(reader, taskMapType); // Deserialize JSON to Map<Integer, Task>
            return tasks != null ? tasks : new HashMap<>(); // Return an empty map if the file is empty
        } catch (IOException e) {
            System.out.println("Error reading tasks from file: " + e.getMessage());
            return new HashMap<>(); // Return an empty map if there's an error
        }
    }

    // Method to write tasks to the JSON file
    public void writeTasksToFile(Map<Integer, Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer); // Serialize Map<Integer, Task> to JSON with pretty printing
            System.out.println("Tasks saved to file: " + new File(FILE_PATH).getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing tasks to file: " + e.getMessage());
        }
    }
}