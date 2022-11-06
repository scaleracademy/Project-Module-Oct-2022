package com.scaler.firstproj.data;
import java.util.ArrayList;

public class Response {

    ArrayList<Task> tasks;
    StatusCode status;
    String errorMessage;

    public Response(Task task, StatusCode status){
        this.tasks = new ArrayList<>();
        this.tasks.add(task);
        this.status = status;
    }

    public Response(ArrayList<Task> tasks, StatusCode status){
        this.tasks = tasks;
        this.status = status;
    }

    public Response(String errorMessage, StatusCode status){
        this.tasks= new ArrayList<>();
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public StatusCode getStatus() {
        return status;
    }

    public void setStatus(StatusCode status) {
        this.status = status;
    }
}
