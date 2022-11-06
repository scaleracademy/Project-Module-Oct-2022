package com.scaler.firstproj.controllers;

import com.scaler.firstproj.data.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    List<Task> taskDataInmemory = new ArrayList<>();

    @PostMapping
    public ResponseEntity createTask(@RequestBody Task taskBody) {
        taskDataInmemory.add(taskBody);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task Created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity getTask(@PathVariable("id") Integer id ) {
        if (id<0 || id > taskDataInmemory.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requested task not found ");
        }

        Task data =  (id == 0 ? taskDataInmemory.get(0) : taskDataInmemory.get(id-1));
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping
    public ResponseEntity getAllTasks() {
        if (taskDataInmemory.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested task not found ");
        }

        return ResponseEntity.status(HttpStatus.OK).body(taskDataInmemory);
    }

    @PutMapping("{id}")
    public ResponseEntity updateTask(@PathVariable("id") Integer id, @RequestBody Task taskBody) {
        if (id<0 || id > taskDataInmemory.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requested task not found ");
        }

        int index = id == 0 ? id : id-1;
        taskDataInmemory.set(index, taskBody);
        return ResponseEntity.status(HttpStatus.OK).body(taskDataInmemory.get(index));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Integer taskId) {
        if (taskId<0 || taskId > taskDataInmemory.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requested task not found ");
        }
        int index = taskId == 0 ? taskId : taskId-1;
        taskDataInmemory.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
    }







    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }


    static  class Task {
        String title;
        Date dueDate;
        Boolean completed;

        public Task(String title, Date dueDate, Boolean completed) {
            this.title = title;
            this.dueDate = dueDate;
            this.completed = completed;
        }

        public String getTitle() {
            return title;
        }

        public Date getDueDate() {
            return dueDate;
        }

        public Boolean getCompleted() {
            return completed;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDueDate(Date dueDate) {
            this.dueDate = dueDate;
        }

        public void setCompleted(Boolean completed) {
            this.completed = completed;
        }
    }
}
