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

    @GetMapping("")
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        return tasks.get(id);
    }

    @PostMapping("")
    public Integer createTask(@RequestBody Task task) {
        tasks.add(task);
        return tasks.size() - 1;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTask(@PathVariable("id") Integer id) {
        if (id < 0 || id >= tasks.size()) {
            return false;
        }

        tasks.remove(id);

        return true;
    }

    @PatchMapping("/{id}")
    public Boolean updateTask(@PathVariable("id") Integer id, @RequestBody Task updatedTask) {
        if (id < 0 || id >= tasks.size()) {
            return false;
        }

        Task task = tasks.get(id);

        boolean isUpdated = false;

        if (updatedTask.getTitle() != null) {
            isUpdated = true;
            task.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDueDate() != null) {
            isUpdated = true;
            task.setDueDate(updatedTask.getDueDate());
        }
        if (updatedTask.getCompleted() != null) {
            isUpdated = true;
            task.setCompleted(updatedTask.getCompleted());
        }

        return isUpdated;
    }
}
