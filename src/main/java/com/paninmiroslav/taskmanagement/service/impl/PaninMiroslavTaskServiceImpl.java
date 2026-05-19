package com.paninmiroslav.taskmanagement.service.impl;

import com.paninmiroslav.taskmanagement.dto.request.PaninMiroslavTaskRequestDto;
import com.paninmiroslav.taskmanagement.dto.response.PaninMiroslavTaskResponseDto;
import com.paninmiroslav.taskmanagement.entity.PaninMiroslavTask;
import com.paninmiroslav.taskmanagement.mapper.PaninMiroslavTaskMapper;
import com.paninmiroslav.taskmanagement.repository.PaninMiroslavTaskRepository;
import com.paninmiroslav.taskmanagement.service.PaninMiroslavTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaninMiroslavTaskServiceImpl implements PaninMiroslavTaskService {

    private final PaninMiroslavTaskRepository repository;
    private final PaninMiroslavTaskMapper mapper;

    @Override
    public PaninMiroslavTaskResponseDto createTask(PaninMiroslavTaskRequestDto dto) {

        PaninMiroslavTask task = mapper.toEntity(dto);
        task.setStatus("OPEN");

        return mapper.toDto(repository.save(task));
    }

    @Override
    public Page<PaninMiroslavTaskResponseDto> getAllTasks(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return repository.findAll(pageable)
                .map(mapper::toDto);
    }
}