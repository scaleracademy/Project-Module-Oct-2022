package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.tasks.dtos.CreateTaskDto;
import com.scaler.taskmgrspring.tasks.dtos.TaskResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;
    private final ModelMapper modelMapper;

    public TasksService(TasksRepository tasksRepository, ModelMapper modelMapper) {
        this.tasksRepository = tasksRepository;
        this.modelMapper = modelMapper;
    }

    public TaskResponseDto createTask(CreateTaskDto newTask) {
        TaskEntity task = modelMapper.map(newTask, TaskEntity.class);
        task.setCompleted(false);
        TaskEntity savedTask =  tasksRepository.save(task);
        return modelMapper.map(savedTask, TaskResponseDto.class);
    }


}
