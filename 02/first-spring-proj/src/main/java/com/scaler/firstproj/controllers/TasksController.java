package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    int current_id_no = 0;

    private Integer createId() {
        current_id_no += 1;
        return current_id_no;
    }

    public TasksController() {
        this.tasks = new ArrayList<>();

        // sample data for testing
        this.tasks.add(new Task("Task 1", new Date(), false, createId()));
        this.tasks.add(new Task("Task 2", new Date(), true, createId()));
    }

    @GetMapping("")
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/pending")
    public ArrayList<Task> getPendingTasks(){
        ArrayList<Task> pendingTasks = new ArrayList<>();
        for (Task task:tasks) {
            if (!task.getCompleted()) {
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        for (Task task:tasks) {
            if (Objects.equals(task.getId(), id)) {
                return task;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") Integer id){
        for (Task task:tasks) {
            if (Objects.equals(task.getId(), id)) {
                tasks.remove(task);
                return "Task deleted Successfully";
            }
        }
        return "Requested task not available in list";
    }

    @PostMapping("")
    public ArrayList<Task> createTask(@RequestBody Task task) {
        task.setId(createId());
        tasks.add(task);
        return tasks;
    }

    @PatchMapping("{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable Integer id){
        for (Task old_task:tasks) {
            if (Objects.equals(old_task.getId(), id)) {
                if (task.getCompleted() != null) {
                    old_task.setCompleted(task.getCompleted());
                }
                if (task.getTitle() != null) {
                    old_task.setTitle(task.getTitle());
                }
                if (task.getDueDate() != null) {
                    old_task.setDueDate(task.getDueDate());
                }
                return old_task;
            }
        }
        return null ;
    }
}
