package com.tasktracker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class FileHandler {
    private static final String FILE_PATH = "tasks.json"; //Path to the JSON file
    private static final Gson gson = new Gson(); //Gson instance for JSON serialization/deserialization

    //Method to read tasks from the JSON file
    public Map<Integer, Task> readTaskFromFile() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type taskMapType = new TypeToken<Map<Integer, Task>>() {}.getType();
            return gson.fromJson(reader, taskMapType); //Deserialize JSON to Map<integer, Task>
        } catch (IOException e) {
            System.out.println("No existing tasks file found. A new one will be created.");
            return null; // Return null if the file doesn't exist
        }
    }

    //Method to write tasks to the JSON file
    public void writeTasksToFile(Map<Integer, Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer); //Serialize Map<Integer, Task> to JSON
        } catch (IOException e) {
            System.out.println("Error writing tasks to file: " + e.getMessage());
        }
    }

}
