package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.notes.NotesDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @PostMapping
    Long createTasks(@RequestBody TaskDTO taskDTO) {
        return tasksService.createTask(taskDTO);
    }

    @GetMapping
    List<TaskDTO> getAllTasks() {
        return tasksService.getAllTasks();
    }

    @GetMapping("/{id}")
    TaskDTO getTaskById(@PathVariable Long id) {
        return tasksService.getTaskById(id);
    }

    @PatchMapping("/{id}")
    TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return tasksService.updateTask(id, taskDTO);
    }

    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable Long id) {
        tasksService.deleteTaskById(id);
    }

    @PatchMapping("/{id}/notes")
    void addNoteToTask(@RequestBody NotesDTO note, @PathVariable Long id) {
        tasksService.addNoteToTask(note, id);
    }
}
