package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Response;
import com.scaler.firstproj.data.StatusCode;
import com.scaler.firstproj.data.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    ASSIGNMENT 02:

    Building a "Task Manager" API that does the following
    1. Create a task (with following attributes) -> POST
        - Title
        - Due Date
        - Status (Pending -> false, Completed -> true)
    2. Update a task -> PUT
    3. Delete a task -> DELETE
    4. List all tasks -> GET
*/

@RestController
@RequestMapping("/tasks")
public class TasksController {

    ArrayList<Task> tasks;

    public TasksController() {
        this.tasks = new ArrayList<>();

        // sample data for testing
        this.tasks.add(new Task("Task 1", new Date()));
        this.tasks.add(new Task("Task 2", new Date()));
    }

    @GetMapping("")
    public Response getAllTasks() {
//        not working ❌
        return new Response(tasks, StatusCode.OK);
    }

    @GetMapping("/{id}")
    public Response getTaskById(@PathVariable("id") Integer id) {
//        not working ❌
        return new Response(tasks.get(id), StatusCode.OK);
    }

    @PostMapping("")
    public Response createTask(@RequestBody Task task) {
        // working ✔
        this.tasks.add(task);
        return new Response("Task created successfully.", StatusCode.CREATED);
    }

    @PutMapping("/{id}")
    public Response updateTask(@PathVariable Integer id, @RequestBody Task newTask) {
        if(id <= Task.idCounter) {
            Task task = tasks.get(id);
            task.setTitle(newTask.getTitle());
            task.setDueDate(newTask.getDueDate());
            task.setCompleted(newTask.getCompleted());
            return new Response(task, StatusCode.OK, "Task updated successfully");
        }

        return new Response("Task id not present.", StatusCode.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public Response deleteTask(@PathVariable Integer id) {
        // todo: write logic to delete the task with given id
        return new Response("Task deleted successfully", StatusCode.DELETED);
    }
}
