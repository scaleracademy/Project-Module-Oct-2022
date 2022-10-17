package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.beans.HelloBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;
    private final HelloBean helloBean;

    public TasksService(TasksRepository tasksRepository, HelloBean helloBean) {
        this.tasksRepository = tasksRepository;
        this.helloBean = helloBean;
    }

    public TaskEntity createTask(String title, String description, Date dueDate) {
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        task.setCompleted(false);
        task.setDueDate(dueDate);
        return tasksRepository.save(task);
    }

    public String getGreeting() {
        return helloBean.greeting;
    }

}
