package com.scaler.taskmgrspring.tasks.dtos;

import com.scaler.taskmgrspring.notes.NotesEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UpdateTaskDto {
    private String title;
    private String description;
    private Boolean completed;
    private Date dueDate;
    private List<NotesEntity> notes;
}
