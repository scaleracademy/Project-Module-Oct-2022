package com.scaler.taskmgrspring.notes;

import com.scaler.taskmgrspring.common.ErrorResponseDto;
import com.scaler.taskmgrspring.notes.dtos.CreateNotesDto;
import com.scaler.taskmgrspring.notes.dtos.NotesResponseDto;
import com.scaler.taskmgrspring.notes.exceptions.NoteNotFoundException;
import com.scaler.taskmgrspring.tasks.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("")
    public ResponseEntity<NotesResponseDto> createNote(
            @PathVariable Long taskId,
            @RequestBody CreateNotesDto notesDto
    ) {
        NotesResponseDto savedNote = notesService.createNote(notesDto, taskId);
        return ResponseEntity
                .created(URI.create("/tasks/" + savedNote.getTaskId() + "/notes/" + savedNote.getId()))
                .body(savedNote);
    }

    @GetMapping("")
    public ResponseEntity<List<NotesResponseDto>> getAllNotesByTaskId(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(notesService.getAllNotesByTaskId(taskId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNoteWithIdInTaskId(
            @PathVariable("id") Long notesId,
            @PathVariable("taskId") Long taskId
    ) {
        notesService.deleteByNoteId(notesId, taskId);
        return ResponseEntity.ok("Note deleted successfully");
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            TaskNotFoundException.class,
            NoteNotFoundException.class
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
