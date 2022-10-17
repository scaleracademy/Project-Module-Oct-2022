package com.scaler.taskmgrspring.tasks;

import com.scaler.taskmgrspring.notes.NotesMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NotesMapper.class})
public interface TaskMapper {
    TaskDTO toDTO(TaskEntity entity);

    TaskEntity toEntity(TaskDTO dto);
}
