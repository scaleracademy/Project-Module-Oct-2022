package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Task;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    // 1. Create a task
    @PostMapping("")
    public String createTask(@RequestBody Task task) {
        try {
            tasks.add(task);
            return "success";
        } catch (Exception e) {
            return "unexpected error occurred: " + e.getMessage();
        }
    }

    // 2. Update a task
    private static Date parseDateFromString(String dateString) throws ParseException {
        List<String> dateFormatStrings = Arrays.asList(
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd"
        );
        for (String dateFormatString : dateFormatStrings) {
            try {
                return new SimpleDateFormat(dateFormatString).parse(dateString);
            } catch (ParseException pe) {}
        }
        throw new ParseException("Date String not in supported formats", -1);
    }

    @PatchMapping("/{id}")
    public String updateTask(@PathVariable("id") int id,
                             @RequestBody Map<String, Object> updates) {
        try {
            Task task = tasks.get(id);
            for (String key: updates.keySet()) {
                switch (key) {
                    case "title":
                        task.setTitle((String) updates.get(key));
                        break;
                    case "dueDate":
                        task.setDueDate(parseDateFromString((String) updates.get(key)));
                        break;
                    case "completed":
                        task.setCompleted((Boolean) updates.get(key));
                }
            }
            return "success";
        } catch (IndexOutOfBoundsException e) {
            return "failed: no tasks with id " + id;
        } catch (Exception e) {
            return "unexpected error occurred: " + e.getMessage();
        }
    }

    // 3. Delete a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") int id) {
        try {
            tasks.remove(id);
        } catch (IndexOutOfBoundsException e) {
            return "failed: no tasks with id " + id;
        }
        return "success";
    }

    // 4. List all tasks
    @GetMapping("")
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        return tasks.get(id);
    }

}
