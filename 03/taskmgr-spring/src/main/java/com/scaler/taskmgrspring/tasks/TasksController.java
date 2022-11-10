package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.common.ErrorResponseDto;
import com.scaler.taskmgrspring.tasks.dtos.*;
import com.scaler.taskmgrspring.tasks.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @PostMapping("")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody CreateTaskDto createTask) {
        TaskResponseDto savedTask = tasksService.createTask(createTask);

        return ResponseEntity
                .created(URI.create("/tasks/" + savedTask.getId()))
                .body(savedTask);
    }

    @GetMapping("")
    public ResponseEntity<List<TaskResponseDto>> getTasks() {
        return ResponseEntity.ok(tasksService.getAllTasks());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TaskResponseDto>> getTasksByCompleted(@RequestParam("completed") Boolean isCompleted) {
        return ResponseEntity.ok(tasksService.getAllTasksByCompleted(isCompleted));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tasksService.getTaskById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable("id") Long id,
            @RequestBody UpdateTaskDto updateTaskDto
    ) {
        return ResponseEntity.ok(tasksService.updateTask(id, updateTaskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        tasksService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @ExceptionHandler({
        IllegalArgumentException.class,
        TaskNotFoundException.class,
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
