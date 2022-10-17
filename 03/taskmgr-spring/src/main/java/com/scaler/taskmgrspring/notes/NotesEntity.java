package com.scaler.taskmgrspring.notes;

import com.scaler.taskmgrspring.tasks.TaskEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "notes")
public class NotesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String description;

    @ManyToOne(targetEntity = TaskEntity.class, cascade = CascadeType.ALL)
    private TaskEntity task;
}
