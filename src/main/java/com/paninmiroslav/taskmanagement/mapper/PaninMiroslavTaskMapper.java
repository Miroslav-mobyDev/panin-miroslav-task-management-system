package com.paninmiroslav.taskmanagement.mapper;

import com.paninmiroslav.taskmanagement.dto.request.PaninMiroslavTaskRequestDto;
import com.paninmiroslav.taskmanagement.dto.response.PaninMiroslavTaskResponseDto;
import com.paninmiroslav.taskmanagement.entity.PaninMiroslavTask;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaninMiroslavTaskMapper {

    PaninMiroslavTask toEntity(PaninMiroslavTaskRequestDto dto);

    PaninMiroslavTaskResponseDto toDto(PaninMiroslavTask entity);
}