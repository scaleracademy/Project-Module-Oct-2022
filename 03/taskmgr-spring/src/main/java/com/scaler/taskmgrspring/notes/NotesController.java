package com.scaler.taskmgrspring.notes;

import com.scaler.taskmgrspring.notes.dtos.CreateNotesRequestDto;
import com.scaler.taskmgrspring.notes.dtos.CreateNotesResponseDto;
import com.scaler.taskmgrspring.tasks.TasksService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    /*
     * ASSIGNMENT 03: Implement the notes endpoints as described here
     * https://github.com/scaleracademy/project-module-requirement-docs/blob/main/task-manager/API.md#get--taskstask_idnotes
     *
     * Ofcourse this means you have to implement the NotesService and NotesRepository
     */

    private NotesService notesService;
    private TasksService tasksService;

    public NotesController(NotesService notesService, TasksService tasksService) {
        this.notesService = notesService;
        this.tasksService = tasksService;
    }

    @GetMapping("") // TODO: return type will not be String
    public String getAllNotesByTaskId(@PathVariable("taskId") Long taskId) {
        return "here are all the notes for task = " + taskId;
    }

//    @PostMapping("")
//    public CreateNotesResponseDto createNote(@PathVariable Long taskId, CreateNotesRequestDto requestDto) {
//
//    }
}
