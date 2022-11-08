package com.scaler.firstproj.data;

import java.util.Date;
import java.util.UUID;

public class Task {

    String id;

    public void setId(String id) {
        this.id = id;
    }

    String title;
    Date dueDate;
    Boolean completed;

    private String generateUniqueID(){
        return UUID.randomUUID().toString();
    }

    public Task(String title, Date dueDate, Boolean completed) {
        this.id = generateUniqueID();
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public Task(Task newTask){
        this.id = generateUniqueID();
        this.title = newTask.title;
        this.dueDate = newTask.dueDate;
        this.completed = newTask.completed;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}