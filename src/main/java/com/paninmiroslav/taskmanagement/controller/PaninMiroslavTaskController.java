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
    public ResponseEntity<PaninMiroslavTaskResponseDto>
    createTask(
            @Valid
            @RequestBody
            PaninMiroslavTaskRequestDto dto
    ) {

        return ResponseEntity.ok(
                service.createTask(dto)
        );
    }

    @GetMapping
    public ResponseEntity<Page<PaninMiroslavTaskResponseDto>>
    getAllTasks(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sort
    ) {

        return ResponseEntity.ok(
                service.getAllTasks(page, size, sort)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaninMiroslavTaskResponseDto>
    getTaskById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.getTaskById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaninMiroslavTaskResponseDto>
    updateTask(

            @PathVariable Long id,

            @Valid
            @RequestBody
            PaninMiroslavTaskRequestDto dto
    ) {

        return ResponseEntity.ok(
                service.updateTask(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteTask(
            @PathVariable Long id
    ) {

        service.deleteTask(id);

        return ResponseEntity.ok(
                "Task deleted successfully"
        );
    }
}