package com.paninmiroslav.taskmanagement.controller;

import com.paninmiroslav.taskmanagement.dto.request.PaninMiroslavTaskRequestDto;
import com.paninmiroslav.taskmanagement.dto.response.PaninMiroslavTaskResponseDto;
import com.paninmiroslav.taskmanagement.service.PaninMiroslavTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class PaninMiroslavTaskController {

    private final PaninMiroslavTaskService service;

    @PostMapping
    public ResponseEntity<PaninMiroslavTaskResponseDto> createTask(
            @Valid @RequestBody PaninMiroslavTaskRequestDto dto
    ) {
        return ResponseEntity.ok(service.createTask(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PaninMiroslavTaskResponseDto>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        return ResponseEntity.ok(service.getAllTasks(page, size, sort));
    }
}