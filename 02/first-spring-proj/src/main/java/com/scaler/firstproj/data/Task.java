package com.scaler.firstproj.data;

import java.util.Date;

public class Task {
    public static int idCounter = 0;
    int id;
    String title;
    Date dueDate;
    Boolean completed;

    public Task(String title, Date dueDate) {
        id = idCounter++;
        this.title = title;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public int getId() {
        return id;
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