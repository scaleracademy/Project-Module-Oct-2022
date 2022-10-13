package com.scaler.firstproj.service;

import com.scaler.firstproj.Repository.TaskRepository;
import com.scaler.firstproj.data.Task;

import java.util.ArrayList;

public class TaskService {

    public static Task createTask(Task task) {
        return TaskRepository.createTask(task);
    }

    public static ArrayList<Task> getAllTasks() {
        return TaskRepository.getAllTasks();
    }

    public static Task getTaskById(Long id) {
        return TaskRepository.getById(id);
    }

    public static Task deleteTaskById(Long id) {
        return TaskRepository.deleteTask(id);
    }

    public static Task updateTask(Task task) {
        return TaskRepository.updateTask(task);
    }
}
