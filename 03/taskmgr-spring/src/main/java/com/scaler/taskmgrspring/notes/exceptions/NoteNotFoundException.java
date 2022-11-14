package com.scaler.taskmgrspring.notes.exceptions;

public class NoteNotFoundException extends IllegalArgumentException {
    public NoteNotFoundException(Long noteId) {
        super("Note with id = " + noteId + " not found");
    }
}
