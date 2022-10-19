package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// First Ever Spring boot code.

@RestController
@RequestMapping("/tasks")
public class TasksController {


    /*
    ASSIGNMENT 02:

    Building a "Task Manager" API that does the following
    1. Create a task (with following attributes)
        - Title
        - Due Date
        - Status (Pending, Completed)
    2. Update a task
    3. Delete a task
    4. List all tasks
     */

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskController() {
        // Some in-built tasks.
        tasks.add(new Task("Get Up", new Date(), true));
        tasks.add(new Task("Brush", new Date(), true));
        tasks.add(new Task("Bath", new Date(), true));
        tasks.add(new Task("Meditate", new Date(), true));
    }

    @GetMapping("")
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") int id) {
        if (id < 0 || id > tasks.size() - 1)
            throw new RuntimeException("No task found with id " + id);

        return tasks.get(id);
    }

    @PostMapping
    public String createTask(@RequestBody Task task) {
        tasks.add(task);

        return "Task created!";  // TODO: Need to improve responses.
    }

    @PutMapping("/{id}")
    public String updateTaskById(@PathVariable("id") int id, @RequestBody Task task) {
        if (id < 0 || id > tasks.size() - 1)
            return "No task found with id " + id;

        tasks.set(id, task);   // overwrite full task.

        return "Task id " + id + " updated.";
    }

    @DeleteMapping("/{id}")
    public String deleteTaskById(@PathVariable("id") int id) {
        if (id < 0 || id > tasks.size() - 1)
            return "Invalid task id " + id;

        tasks.remove(id);

        return "Task id " + id + " deleted.";
    }
}
