package com.scaler.firstproj.Repository;

import com.scaler.firstproj.data.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskRepository {
    private static Map<Long, Task> idVsTask = new HashMap();
    private static long lastCount = 0l;

    public static Task createTask(Task task) {
        lastCount += 1;
        task.setId(lastCount);
        idVsTask.put(lastCount,task);
        return idVsTask.get(lastCount);
    }

    public static Task getById(Long id) {
        return idVsTask.get(id);
    }

    public static ArrayList<Task> getAllTasks() {
        return new ArrayList<Task>(idVsTask.values());
    }

    public static Task deleteTask(Long id){
        Task task = idVsTask.get(id);
        idVsTask.remove(id);
        return task;
    }

    public static Task updateTask(Task task) {
        Task inRepo = idVsTask.get(task.getId());
        if(task.getTitle() != null) {
            inRepo.setTitle(task.getTitle());
        }
        if(task.getDueDate()!= null) {
            inRepo.setDueDate(task.getDueDate());
        }
        if(task.getCompleted() != null) {
            inRepo.setCompleted(task.getCompleted());
        }
        idVsTask.put(inRepo.getId(),inRepo);
        return inRepo;
    }


}
