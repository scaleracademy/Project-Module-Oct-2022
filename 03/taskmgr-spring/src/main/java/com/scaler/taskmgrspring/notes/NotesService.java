package com.scaler.taskmgrspring.notes;

import com.scaler.taskmgrspring.tasks.TaskEntity;
import com.scaler.taskmgrspring.tasks.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private final NotesMapper notesMapper;

    private final NotesRepository notesRepository;


    @Autowired
    public NotesService(NotesMapper notesMapper, NotesRepository notesRepository) {
        this.notesMapper = notesMapper;
        this.notesRepository = notesRepository;
    }


    public List<NotesDTO> createNotes(List<NotesDTO> notes, TaskEntity taskEntity) {
        List<NotesEntity> notesEntities = notes.stream().map(note -> {
            NotesEntity notesEntity = notesMapper.toEntity(note);
            notesEntity.setTask(taskEntity);
            return notesEntity;
        }).toList();

        return notesRepository.saveAll(notesEntities).stream().map(notesMapper::toDTO).toList();
    }

    public List<NotesDTO> getAllNotesByTaskId(Long taskId) {
        return notesRepository.findAllByTaskId(taskId).stream()
                .map(notesMapper::toDTO).toList();
    }

    public void deleteNoteById(Long notesId) {
        notesRepository.deleteById(notesId);
    }
}
