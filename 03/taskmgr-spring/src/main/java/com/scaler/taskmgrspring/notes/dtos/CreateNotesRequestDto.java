package com.scaler.taskmgrspring.notes.dtos;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class CreateNotesRequestDto {
    @NonNull
    private String title;
    private String description;
}
