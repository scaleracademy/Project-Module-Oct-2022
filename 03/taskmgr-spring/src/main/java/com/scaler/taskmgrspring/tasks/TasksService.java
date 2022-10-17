package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.notes.NotesDTO;
import com.scaler.taskmgrspring.notes.NotesMapper;
import com.scaler.taskmgrspring.notes.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;

    private final TaskMapper taskMapper;

    private final NotesService notesService;

    @Autowired
    public TasksService(TasksRepository tasksRepository, TaskMapper taskMapper, NotesService notesService) {
        this.tasksRepository = tasksRepository;
        this.taskMapper = taskMapper;
        this.notesService = notesService;
    }

    public TaskEntity createTask(String title, String description, Date dueDate) {
        TaskEntity task = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .build();
        return tasksRepository.save(task);
    }

    public Long createTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = tasksRepository.save(taskMapper.toEntity(taskDTO));
        return taskEntity.getId();
    }

    public List<TaskDTO> getAllTasks() {
        return tasksRepository.findAll().stream()
                .map(taskMapper::toDTO).toList();
    }

    public TaskDTO getTaskById(Long id) {
        Optional<TaskEntity> taskOptional = tasksRepository.findById(id);
        return taskOptional.map(taskMapper::toDTO)
                .orElseThrow(IllegalArgumentException::new);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        TaskEntity taskEntity = getTaskEntityById(id);

        taskEntity.setCompleted(taskDTO.getCompleted());
        notesService.createNotes(taskDTO.getNotes(), taskEntity);

        return taskMapper.toDTO(taskEntity);
    }

    public void deleteTaskById(Long id) {
        tasksRepository.deleteById(id);
    }

    private TaskEntity getTaskEntityById(Long id) {
        Optional<TaskEntity> taskEntityOptional = tasksRepository.findById(id);
        return taskEntityOptional.orElseThrow(
                () -> new IllegalArgumentException("invalid id"));
    }

    public void addNoteToTask(NotesDTO note, Long id) {
        TaskEntity taskEntity = getTaskEntityById(id);

        notesService.createNotes(List.of(note), taskEntity);
    }
}
