package com.marware.tasktrackcli.service;

import com.marware.tasktrackcli.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void shouldAddTaskSuccessfully() {
        taskService.addTask("Learn unit testing");
        List<Task> tasks = taskService.getAllTasks(); // Este método lo vamos a crear ahora

        assertFalse(tasks.isEmpty(), "Task list should not be empty");
        assertEquals("Learn unit testing", tasks.get(tasks.size() - 1).getDescription());
    }

    @Test
    void shouldNotAddEmptyTask() {
        int originalSize = taskService.getAllTasks().size();

        taskService.addTask("   "); // espacio en blanco

        List<Task> tasksAfter = taskService.getAllTasks();

        assertEquals(originalSize, tasksAfter.size(), "Empty task should not be added");
    }

}
