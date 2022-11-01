package com.scaler.taskmgrspring.tasks.dtos;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
public class CreateTaskDto {
    @NonNull
    String title;

    String description;

    @NonNull
    Date dueDate;
}
