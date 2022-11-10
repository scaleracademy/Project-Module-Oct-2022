package com.scaler.firstproj.data;

import java.util.Date;

public class RequestTaskDto {
    private int id;
    private String title;
    private Date dueDate;
    private Boolean isCompleted;

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
        return isCompleted;
    }

    public RequestTaskDto(int id, String title, Date dueDate, Boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }
}

/*{
    "id": 2,
    "title": "Something",
    "dueDate": "",
    "isComplete": ""
* }*/
