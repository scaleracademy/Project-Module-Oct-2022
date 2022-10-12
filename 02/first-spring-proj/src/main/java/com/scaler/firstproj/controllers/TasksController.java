package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

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
        this.tasks.add(new Task("Task_1", new Date(), false));
        this.tasks.add(new Task("Task_2", new Date(), true));
    }

    @GetMapping("")
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        return tasks.get(id);
    }

    @DeleteMapping("delete/{title}")
    public boolean deleteTaskById(@PathVariable("title") String title) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                tasks.remove(task);
                return true;
            }
        }
        return false;
    }
    @PutMapping("/update/{title}")
    public boolean updateTaskById(@PathVariable("title") String title, @RequestBody Task task) {
        for (Task task1 : tasks) {
            if (task1.getTitle().equals(title)) {
                task1.setCompleted(task.getCompleted());
                task1.setDueDate(task.getDueDate());
                task1.setTitle(task.getTitle());
                return true;
            }
        }
        return false;
    }
    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        tasks.add(task);
        return tasks.get(tasks.size()-1);
    }
}
