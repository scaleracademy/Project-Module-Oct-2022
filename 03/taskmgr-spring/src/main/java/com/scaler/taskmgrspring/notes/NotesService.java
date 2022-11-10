package com.scaler.taskmgrspring.notes;

import org.springframework.stereotype.Service;

@Service
public class NotesService {
    private NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }
}
