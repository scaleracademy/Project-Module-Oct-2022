package com.scaler.taskmgrspring.tasks;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
public class TasksServiceTests {
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private ModelMapper modelMapper;

//    @Test
//    public void testCreateTask() {
//        TasksService tasksService = new TasksService(tasksRepository, modelMapper);
//
//        TaskEntity task = tasksService.createTask(
//                "Test Task",
//                "Test Description",
//                new Date()
//        );
//
//        System.out.println(task);
//    }
}
