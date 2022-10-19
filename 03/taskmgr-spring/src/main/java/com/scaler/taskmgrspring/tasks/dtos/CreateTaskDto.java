package com.scaler.taskmgrspring.tasks.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CreateTaskDto {
    String title;
    String description;
    Date dueDate;
}
