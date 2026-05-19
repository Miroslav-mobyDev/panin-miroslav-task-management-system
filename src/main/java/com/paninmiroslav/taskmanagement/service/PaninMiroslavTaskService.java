package com.paninmiroslav.taskmanagement.service;

import com.paninmiroslav.taskmanagement.dto.request.PaninMiroslavTaskRequestDto;
import com.paninmiroslav.taskmanagement.dto.response.PaninMiroslavTaskResponseDto;
import org.springframework.data.domain.Page;

public interface PaninMiroslavTaskService {

    PaninMiroslavTaskResponseDto createTask(PaninMiroslavTaskRequestDto dto);

    Page<PaninMiroslavTaskResponseDto> getAllTasks(int page, int size, String sort);
}