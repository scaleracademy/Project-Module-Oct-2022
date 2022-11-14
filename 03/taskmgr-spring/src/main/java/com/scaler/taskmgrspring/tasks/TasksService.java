package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.tasks.dtos.CreateTaskDto;
import com.scaler.taskmgrspring.tasks.dtos.TaskResponseDto;
import com.scaler.taskmgrspring.tasks.dtos.UpdateTaskDto;
import com.scaler.taskmgrspring.tasks.exceptions.TaskNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;
    private final ModelMapper modelMapper;

    public TasksService(TasksRepository tasksRepository, ModelMapper modelMapper) {
        this.tasksRepository = tasksRepository;
        this.modelMapper = modelMapper;
    }

    public TaskEntity findTask(Long id) {
        return tasksRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
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
        TaskEntity task = findTask(id);
        return modelMapper.map(task, TaskResponseDto.class);
    }

    public List<TaskResponseDto> getAllTasks() {
        List<TaskEntity> tasks = tasksRepository.findAll();
        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
        for(TaskEntity task: tasks)
            taskResponseDtos.add(modelMapper.map(task, TaskResponseDto.class));
        return taskResponseDtos;
    }

    public List<TaskResponseDto> getAllTasksByCompleted(Boolean isCompleted) {
        List<TaskEntity> tasks = tasksRepository.findAllByCompleted(isCompleted);
        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
        for(TaskEntity task: tasks)
            taskResponseDtos.add(modelMapper.map(task, TaskResponseDto.class));
        return taskResponseDtos;
    }

    public TaskResponseDto updateTask(Long id, UpdateTaskDto updateTaskDto)
    {
        TaskEntity updatedTask = findTask(id);
        if(updateTaskDto.getTitle() != null)
            updatedTask.setTitle(updateTaskDto.getTitle());

        if(updateTaskDto.getDescription() != null)
            updatedTask.setDescription(updateTaskDto.getDescription());

        if(updateTaskDto.getCompleted() != null)
            updatedTask.setCompleted(updateTaskDto.getCompleted());

        if(updateTaskDto.getDueDate() != null) {
            if (updatedTask.getDueDate().before(new Date())) {
                throw new IllegalArgumentException("Due date cannot be in the past");
            }
            updatedTask.setDueDate(updateTaskDto.getDueDate());
        }

        if(updateTaskDto.getNotes() != null) {
            updatedTask.setNotes(updateTaskDto.getNotes());
            // todo: check noteId, then update notes
        }

        return modelMapper.map(tasksRepository.save(updatedTask), TaskResponseDto.class);
    }

    public void deleteTask(Long id) {
        findTask(id);
        tasksRepository.deleteById(id);
    }

}
