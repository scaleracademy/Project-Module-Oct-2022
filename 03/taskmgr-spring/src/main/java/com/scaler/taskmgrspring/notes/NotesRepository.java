package com.scaler.taskmgrspring.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity, Long> {
    List<NotesEntity> findAllByTaskId(Long taskId);
}
