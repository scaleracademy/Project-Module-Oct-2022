package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.tasks.dtos.CreateTaskDto;
import com.scaler.taskmgrspring.tasks.dtos.TaskResponseDto;
import com.scaler.taskmgrspring.tasks.exceptions.TaskNotFoundException;
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
        // Data Validation
        if (newTask.getDueDate().before(new Date())) {
            throw new IllegalArgumentException("Due date cannot be in the past");
        }
        TaskEntity task = modelMapper.map(newTask, TaskEntity.class);
        task.setCompleted(false);
        TaskEntity savedTask = tasksRepository.save(task);
        return modelMapper.map(savedTask, TaskResponseDto.class);
    }

    public TaskResponseDto getTaskById(Long id) {
        TaskEntity task = tasksRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return modelMapper.map(task, TaskResponseDto.class);
    }

}
