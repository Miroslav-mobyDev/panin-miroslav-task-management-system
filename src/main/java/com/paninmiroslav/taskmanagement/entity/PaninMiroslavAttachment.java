package com.paninmiroslav.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attachments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaninMiroslavAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String filePath;

    @ManyToOne
    private PaninMiroslavTask task;
}
