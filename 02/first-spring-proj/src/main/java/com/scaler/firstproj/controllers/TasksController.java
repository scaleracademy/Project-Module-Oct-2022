package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Task;
import com.scaler.firstproj.service.TaskService;
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



    public TasksController() {
        TaskService.createTask(new Task("Task 1",new Date(),false));
        TaskService.createTask(new Task("Task 2",new Date(),false));
        TaskService.createTask(new Task("Task 3",new Date(),false));
    }

    @GetMapping("")
    public ArrayList<Task> getAllTasks() {
        return TaskService.getAllTasks();
    }


    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Long id) {
        return TaskService.getTaskById(id);
    }

    @PostMapping("")
    public Task createTask(@RequestBody Task task) {
        return TaskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable("id") Long id) {
        return TaskService.deleteTaskById(id);
    }

    @PatchMapping("")
    public Task updateTask(@RequestBody Task task) {
        return TaskService.updateTask(task);
    }

}
