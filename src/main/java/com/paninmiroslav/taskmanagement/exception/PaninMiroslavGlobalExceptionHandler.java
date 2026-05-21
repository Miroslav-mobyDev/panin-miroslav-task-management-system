package com.paninmiroslav.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class PaninMiroslavGlobalExceptionHandler {

    @ExceptionHandler(
            PaninMiroslavTaskNotFoundException.class
    )
    public ResponseEntity<PaninMiroslavErrorResponse>
    handleTaskNotFoundException(
            PaninMiroslavTaskNotFoundException ex
    ) {

        PaninMiroslavErrorResponse response =
                PaninMiroslavErrorResponse.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PaninMiroslavErrorResponse>
    handleGeneralException(
            Exception ex
    ) {

        PaninMiroslavErrorResponse response =
                PaninMiroslavErrorResponse.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .timestamp(LocalDateTime.now())
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
