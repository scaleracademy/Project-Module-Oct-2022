package com.scaler.taskmgrspring.notes;

import com.scaler.taskmgrspring.notes.dtos.CreateNotesDto;
import com.scaler.taskmgrspring.notes.dtos.NotesResponseDto;
import com.scaler.taskmgrspring.tasks.TaskEntity;
import com.scaler.taskmgrspring.tasks.TasksRepository;
import com.scaler.taskmgrspring.tasks.TasksService;
import com.scaler.taskmgrspring.tasks.exceptions.TaskNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotesService {
    private NotesRepository notesRepository;
    private TasksRepository tasksRepository;
    private TasksService tasksService;
    private ModelMapper modelMapper;


    public NotesService(
            NotesRepository notesRepository,
            TasksRepository tasksRepository,
            ModelMapper modelMapper,
            TasksService tasksService
    ) {
        this.notesRepository = notesRepository;
        this.tasksRepository = tasksRepository;
        this.modelMapper = modelMapper;
        this.tasksService = tasksService;
    }

    public NotesResponseDto createNote(CreateNotesDto notesDto, Long taskId) {
        TaskEntity task = tasksRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        NotesEntity newNote = modelMapper.map(notesDto, NotesEntity.class);
        newNote.setTask(task);
        NotesEntity savedNote = notesRepository.save(newNote);

        return modelMapper.map(savedNote, NotesResponseDto.class);
    }

    public List<NotesResponseDto> getAllNotesByTaskId(Long taskId) {
        TaskEntity task = tasksRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        List<NotesEntity> notes = task.getNotes();
        List<NotesResponseDto> notesResponseDtos = new ArrayList<>();
        for(NotesEntity note: notes) {
            notesResponseDtos.add(modelMapper.map(note, NotesResponseDto.class));
        }

        return notesResponseDtos;
    }

    public void deleteByNoteId(Long noteId, Long taskId) {
        tasksService.findTask(taskId);
        notesRepository.deleteById(noteId);
    }

    public void deleteAllNotesByTaskId(Long notesId, Long taskId) {
        tasksService.findTask(taskId);
        notesRepository.deleteAll();
    }
}
