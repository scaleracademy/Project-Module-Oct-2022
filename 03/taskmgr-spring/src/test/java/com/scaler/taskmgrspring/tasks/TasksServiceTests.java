package com.scaler.taskmgrspring.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
public class TasksServiceTests {
    @Autowired
    private TasksRepository tasksRepository;

    @Test
    public void testCreateTask() {
        TasksService tasksService = new TasksService(tasksRepository);

        TaskEntity task = tasksService.createTask(
                "Test Task",
                "Test Description",
                new Date()
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals("Test Task", task.getTitle()),
                () -> Assertions.assertEquals("Test Description", task.getDescription()),
                () -> Assertions.assertEquals(false, task.getCompleted()),
                () -> Assertions.assertNotNull(task.getDueDate()),
                () -> Assertions.assertNotNull(task.getId())

        );
    }
}
