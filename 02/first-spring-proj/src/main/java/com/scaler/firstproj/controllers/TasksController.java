package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    ArrayList<Task> tasks;

    public TasksController() {
        this.tasks = new ArrayList<>();

        // sample data for testing
        this.tasks.add(new Task("Task 1", new Date(), false));
        this.tasks.add(new Task("Task 2", new Date(), true));
    }

    @PostMapping
    public String createTask(@RequestBody Task task) {
        tasks.add(task);
        return "Task added successfully!";
    }

    @PutMapping("{id}")
    public String updateTask(@PathVariable("id") int id, @RequestBody Task task) {
        tasks.set(id, task);
        return "Task updated!";
    }

    @DeleteMapping("{id}")
    public String deleteTask(@PathVariable("id") int id) {
        tasks.remove(id);
        return "Task deleted!";
    }

    @GetMapping("")
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        return tasks.get(id);
    }
}
