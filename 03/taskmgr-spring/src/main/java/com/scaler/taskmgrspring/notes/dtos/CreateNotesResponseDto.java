package com.scaler.taskmgrspring.notes.dtos;

import com.scaler.taskmgrspring.notes.NotesEntity;
import lombok.Data;

@Data
public class CreateNotesResponseDto {
    private Long taskId;
    private NotesEntity note;
}
