package com.scaler.firstproj.data;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private List<RequestTaskDto> taskDtos;
    private StatusCode statusCode;
    private String message;

    public Response(Task task, StatusCode statusCode) {
        this.taskDtos = new ArrayList<>();
        RequestTaskDto taskDto = new RequestTaskDto(task.id, task.title, task.dueDate, task.completed);
        this.taskDtos.add(taskDto);
        this.statusCode = statusCode;
    }

    public Response(ArrayList<Task> tasks, StatusCode statusCode) {
        this.taskDtos = new ArrayList<>();
        for(Task task: tasks) {
            taskDtos.add(new RequestTaskDto(task.id, task.title, task.dueDate, task.completed));
        }
        this.statusCode = statusCode;
    }

    public Response(String message, StatusCode statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public Response(Task task, StatusCode statusCode, String message) {
        this.taskDtos = new ArrayList<>();
        this.taskDtos.add(new RequestTaskDto(task.id, task.title, task.dueDate, task.completed));
        this.message = message;
    }

    public List<RequestTaskDto> getTaskDtos() {
        return taskDtos;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
