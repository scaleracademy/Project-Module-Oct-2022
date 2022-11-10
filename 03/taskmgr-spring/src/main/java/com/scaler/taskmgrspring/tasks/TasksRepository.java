package com.scaler.taskmgrspring.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {
    @Query("select t from tasks t where t.completed = :val")
    List<TaskEntity> findAllByCompleted(@Param("val") Boolean isCompleted);
}
