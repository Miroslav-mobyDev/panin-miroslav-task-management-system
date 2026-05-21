package com.paninmiroslav.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaninMiroslavComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private PaninMiroslavTask task;

    @ManyToOne
    private PaninMiroslavUser user;
}
