package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.notes.NotesEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String description;
    private Boolean completed;
    private Date dueDate;

    @OneToMany(targetEntity = NotesEntity.class, mappedBy = "task")
    private List<NotesEntity> notes;
}
