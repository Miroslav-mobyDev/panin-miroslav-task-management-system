package com.paninmiroslav.taskmanagement.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaninMiroslavErrorResponse {

    private String message;

    private int status;

    private LocalDateTime timestamp;
}