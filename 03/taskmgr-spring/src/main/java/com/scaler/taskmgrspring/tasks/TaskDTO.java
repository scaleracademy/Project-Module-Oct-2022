package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.notes.NotesDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    private String title;

    private String description;

    private Boolean completed;

    private Date dueDate;

    private List<NotesDTO> notes;
}
