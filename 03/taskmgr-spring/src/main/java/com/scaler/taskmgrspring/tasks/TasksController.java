package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.notes.NotesDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    /*
         * POST /tasks
Create a new task

GET /tasks
Get all tasks Available filters -

/articles?completed=true/false
GET /tasks/{task_id}
Get the details of a particular task including notes

PATCH /tasks/{task_id}
Edit a task - Add / Remove notes from the task. Mark a task completed.

PATCH /tasks/{task_id}
DELETE /tasks/{task_id}
Delete a particular task

---- ADDITIONAL TASKS (BONUS) -----

GET /tasks/{task_id}/notes
Fetch all the notes under a particular task

POST /tasks/{task_id}/notes
Create a new note under the task with given task id

DELETE /tasks/{task_id}/notes/{notes_id}
Delete a note
            */
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
