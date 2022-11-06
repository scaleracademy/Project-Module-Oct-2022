package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Response;
import com.scaler.firstproj.data.StatusCode;
import com.scaler.firstproj.data.Task;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/tasks")
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

    HashMap<String, Task> tasksMap;

    public TasksController() {
        this.tasksMap = new HashMap<>();

        // sample data for testing
        Task task =  new Task("Task 1", new Date(), false);
        this.tasksMap.put(task.getId(), task);

        task = new Task("Task 2", new Date(), true);
        this.tasksMap.put(task.getId(), task);
    }

    @GetMapping("")
    public Response getAllTasks() {
        ArrayList<Task> results = new ArrayList<Task>(tasksMap.values());
        Response response = new Response(results, StatusCode.OK);
        return response;
    }

    @GetMapping("/{id}")
    public Response getTaskById(@PathVariable("id") String id) {
        if(!tasksMap.containsKey(id)){
            return new Response("Please send the valid taskId", StatusCode.NotFound);
        }

        return new Response(tasksMap.get(id), StatusCode.OK);
    }

    @PostMapping("")
    public Response createTask(@RequestBody Task task){
        tasksMap.put(task.getId(), task);
        return new Response(task, StatusCode.Created);
    }

    @PutMapping("/{id}")
    public Response updateTask(@PathVariable("id") String id, @RequestBody Task task){
        if(!tasksMap.containsKey(id)){
            return new Response("Please send the valid taskId", StatusCode.BadRequest);
        }

        task.setId(id);
        tasksMap.put(id, task);
        return new Response(task, StatusCode.OK);
    }

    @DeleteMapping("/{id}")
    public Response deleteTask(@PathVariable("id") String id){
        if(!tasksMap.containsKey(id)){
            return new Response("Please send the valid taskId", StatusCode.BadRequest);
        }

        tasksMap.remove(id);
        return new Response(new ArrayList<>(),StatusCode.Deleted);
    }
}
