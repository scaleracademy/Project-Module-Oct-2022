package com.scaler.taskmgrspring.notes;

import com.scaler.taskmgrspring.tasks.TaskDTO;
import com.scaler.taskmgrspring.tasks.TaskEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class NotesDTO {
    private Long id;

    private String title;

    private String description;
}
