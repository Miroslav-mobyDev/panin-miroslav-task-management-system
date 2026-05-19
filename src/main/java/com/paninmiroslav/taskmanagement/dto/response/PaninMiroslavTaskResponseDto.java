package com.paninmiroslav.taskmanagement.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaninMiroslavTaskResponseDto {

    private Long id;

    private String title;

    private String description;

    private String status;

    private String priority;
}