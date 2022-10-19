package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.tasks.dtos.CreateTaskDto;
import com.scaler.taskmgrspring.tasks.dtos.TaskResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("")
    public String getTasks() {
        return ""; // TODO: tasksService.getTasks();
    }

    @PostMapping("")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody CreateTaskDto createTask) {
        TaskResponseDto savedTask = tasksService.createTask(createTask);

        return ResponseEntity
                .created(URI.create("/tasks/" + savedTask.getId()))
                .body(savedTask);

    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable("id") Long id) {
        return ""; //TODO:  tasksService.getTask(id);
    }

}
