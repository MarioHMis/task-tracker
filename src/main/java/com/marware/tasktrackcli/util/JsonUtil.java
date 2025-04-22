package com.marware.tasktrackcli.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.marware.tasktrackcli.model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final String FILE_PATH = "tasks.json";
    private static final String BACKUP_PATH = "tasks_backup.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                    return LocalDateTime.parse(json.getAsString());
                }
            })
            .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.toString());
                }
            })
            .create();

    public static void saveTasks(List<Task> tasks) {
        try {
            // Backup
            File original = new File(FILE_PATH);
            File backup = new File(BACKUP_PATH);
            if (original.exists()) {
                Files.copy(original.toPath(), backup.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // Save the new file
            FileWriter writer = new FileWriter(FILE_PATH);
            gson.toJson(tasks, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("❌ Error saving tasks: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            Reader reader = new FileReader(file);
            Type taskListType = new TypeToken<List<Task>>() {}.getType();
            List<Task> tasks = gson.fromJson(reader, taskListType);
            reader.close();

            return tasks != null ? tasks : new ArrayList<>();

        } catch (IOException | JsonSyntaxException e) {
            System.out.println("❌ Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
