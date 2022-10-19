package com.scaler.taskmgrspring.notes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    /*
     * ASSIGNMENT 03: Implement the notes endpoints as described here
     * https://github.com/scaleracademy/project-module-requirement-docs/blob/main/task-manager/API.md#get--taskstask_idnotes
     *
     * Ofcourse this means you have to implement the NotesService and NotesRepository
     */

    @GetMapping("") // TODO: return type will not be String
    public String getAllNotesByTaskId(@PathVariable("taskId") Long taskId) {
        return "here are all the notes for task = " + taskId;
    }
}
