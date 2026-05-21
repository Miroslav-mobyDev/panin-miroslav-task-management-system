package com.paninmiroslav.taskmanagement.exception;

public class PaninMiroslavTaskNotFoundException
        extends RuntimeException {

    public PaninMiroslavTaskNotFoundException(
            String message
    ) {
        super(message);
    }
}