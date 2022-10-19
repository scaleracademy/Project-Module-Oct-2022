package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.notes.NotesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String description;
    private Boolean completed;
    private Date dueDate;

    @OneToMany(targetEntity = NotesEntity.class, cascade = CascadeType.ALL, mappedBy = "task")
    private List<NotesEntity> notes;
}
