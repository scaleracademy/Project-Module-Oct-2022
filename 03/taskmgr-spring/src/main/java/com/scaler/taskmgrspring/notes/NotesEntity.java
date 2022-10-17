package com.scaler.taskmgrspring.notes;

import com.scaler.taskmgrspring.tasks.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "notes")
public class NotesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String description;

    @ManyToOne(targetEntity = TaskEntity.class)
    private TaskEntity task;
}
