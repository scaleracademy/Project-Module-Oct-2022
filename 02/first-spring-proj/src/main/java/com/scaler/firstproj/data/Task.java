package com.scaler.firstproj.data;

import java.util.Date;


public class Task {
    String title;
    Date dueDate;
    Boolean completed;
    Integer id;

    public Task(String title, Date dueDate, Boolean completed, Integer id) {
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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