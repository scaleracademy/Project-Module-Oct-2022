package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.common.ErrorResponseDto;
import com.scaler.taskmgrspring.tasks.dtos.CreateTaskDto;
import com.scaler.taskmgrspring.tasks.dtos.TaskResponseDto;
import com.scaler.taskmgrspring.tasks.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tasksService.getTaskById(id));
    }

    @ExceptionHandler({
        IllegalArgumentException.class,
        TaskNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        if (e instanceof TaskNotFoundException) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto(e.getMessage()));
        }

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDto(e.getMessage()));
    }

}
