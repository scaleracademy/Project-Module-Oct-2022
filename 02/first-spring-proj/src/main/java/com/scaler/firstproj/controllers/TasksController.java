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
    int uid;
    public TasksController() {
        this.tasks = new ArrayList<>();
        uid=0;
        // sample data for testing
        this.tasks.add(new Task(uid++,"Task 0", new Date(), false));
        this.tasks.add(new Task(uid++,"Task 1", new Date(), true));
    }

    @GetMapping("")
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    @PostMapping("")
    public Task addTask(@RequestBody Task task)
    {
        task.setId(uid++);
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task putTask(@PathVariable Integer id,@RequestBody Task task)
    {
            for(Task t:tasks)
            {
                if(t.getId().equals(id))
                {
                    t.setId(id);
                    t.setTitle(task.getTitle());
                    t.setDueDate(task.getDueDate());
                    t.setCompleted(task.getCompleted());

                    return t;
                }
            }

        task.setId(id);
        tasks.add(task);

        return getTaskById(id);
    }

    @PatchMapping("/{id}")
    public String updadteTask(@PathVariable Integer id,@RequestBody Task task)
    {
        for(Task t:tasks)
        {
            if(t.getId().equals(id))
            {
                if(task.getTitle()!=null)
                    t.setTitle(task.getTitle());
                if(task.getDueDate()!=null)
                    t.setDueDate(task.getDueDate());
                if(task.getCompleted()!=null)
                    t.setCompleted(task.getCompleted());

                return "Task Updated "+"\n"+t.toString();
            }
        }

        return "Task Not found";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Integer id)
    {
        tasks.removeIf(t -> t.getId().equals(id));

        return " Task Deleted";
    }
}
