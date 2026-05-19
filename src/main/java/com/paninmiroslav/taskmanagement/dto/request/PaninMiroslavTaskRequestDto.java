package com.paninmiroslav.taskmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaninMiroslavTaskRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String priority;
}