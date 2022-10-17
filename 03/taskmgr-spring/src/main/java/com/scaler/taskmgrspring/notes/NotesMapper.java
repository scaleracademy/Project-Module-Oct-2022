package com.scaler.taskmgrspring.notes;

import com.scaler.taskmgrspring.tasks.TaskMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TaskMapper.class})
public interface NotesMapper {
    NotesDTO toDTO(NotesEntity entity);

    NotesEntity toEntity(NotesDTO dto);
}
