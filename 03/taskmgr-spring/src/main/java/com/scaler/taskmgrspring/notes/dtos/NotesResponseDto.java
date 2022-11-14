package com.scaler.taskmgrspring.notes.dtos;

import lombok.Data;

@Data
public class NotesResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long taskId;
}
