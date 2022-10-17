package com.scaler.taskmgrspring.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    /*
     * ASSIGNMENT 03: Implement the notes endpoints as described here
     * https://github.com/scaleracademy/project-module-requirement-docs/blob/main/task-manager/API.md#get--taskstask_idnotes
     *
     * Ofcourse this means you have to implement the NotesService and NotesRepository
     *

     */
    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }


    @GetMapping("/{taskId}")
    List<NotesDTO> getAllNotesByTaskId(@PathVariable Long taskId) {
        return notesService.getAllNotesByTaskId(taskId);
    }

    @DeleteMapping("{id}")
    void deleteNoteById(@PathVariable Long id) {
        notesService.deleteNoteById(id);
    }
}
