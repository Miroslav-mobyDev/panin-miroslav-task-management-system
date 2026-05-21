package com.paninmiroslav.taskmanagement.service.impl;

import com.paninmiroslav.taskmanagement.dto.request.PaninMiroslavTaskRequestDto;
import com.paninmiroslav.taskmanagement.dto.response.PaninMiroslavTaskResponseDto;
import com.paninmiroslav.taskmanagement.entity.PaninMiroslavTask;
import com.paninmiroslav.taskmanagement.exception.PaninMiroslavTaskNotFoundException;
import com.paninmiroslav.taskmanagement.mapper.PaninMiroslavTaskMapper;
import com.paninmiroslav.taskmanagement.repository.PaninMiroslavTaskRepository;
import com.paninmiroslav.taskmanagement.service.PaninMiroslavTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaninMiroslavTaskServiceImpl
        implements PaninMiroslavTaskService {

    private final PaninMiroslavTaskRepository repository;

    private final PaninMiroslavTaskMapper mapper;

    @Override
    public PaninMiroslavTaskResponseDto createTask(
            PaninMiroslavTaskRequestDto dto
    ) {

        log.info(
                "Creating new task: {}",
                dto.getTitle()
        );

        PaninMiroslavTask task =
                mapper.toEntity(dto);

        task.setStatus("OPEN");

        PaninMiroslavTask savedTask =
                repository.save(task);

        return mapper.toDto(savedTask);
    }

    @Override
    public Page<PaninMiroslavTaskResponseDto> getAllTasks(
            int page,
            int size,
            String sort
    ) {

        log.info(
                "Fetching tasks page={}, size={}, sort={}",
                page,
                size,
                sort
        );

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sort)
        );

        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    @Override
    public PaninMiroslavTaskResponseDto getTaskById(
            Long id
    ) {

        log.info(
                "Fetching task by id: {}",
                id
        );

        PaninMiroslavTask task =
                repository.findById(id)
                        .orElseThrow(() ->
                                new PaninMiroslavTaskNotFoundException(
                                        "Task not found with id: " + id
                                )
                        );

        return mapper.toDto(task);
    }

    @Override
    public PaninMiroslavTaskResponseDto updateTask(
            Long id,
            PaninMiroslavTaskRequestDto dto
    ) {

        log.info(
                "Updating task with id: {}",
                id
        );

        PaninMiroslavTask task =
                repository.findById(id)
                        .orElseThrow(() ->
                                new PaninMiroslavTaskNotFoundException(
                                        "Task not found with id: " + id
                                )
                        );

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());

        PaninMiroslavTask updatedTask =
                repository.save(task);

        return mapper.toDto(updatedTask);
    }

    @Override
    public void deleteTask(
            Long id
    ) {

        log.info(
                "Deleting task with id: {}",
                id
        );

        PaninMiroslavTask task =
                repository.findById(id)
                        .orElseThrow(() ->
                                new PaninMiroslavTaskNotFoundException(
                                        "Task not found with id: " + id
                                )
                        );

        repository.delete(task);

        log.info(
                "Task deleted successfully: {}",
                id
        );
    }
}