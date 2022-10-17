package com.scaler.taskmgrspring.notes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    @GetMapping("") // TODO: return type will not be String
    public String getAllNotesByTaskId(@PathVariable("taskId") Long taskId) {
        return "here are all the notes for task = " + taskId;
    }
}
