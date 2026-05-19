package com.paninmiroslav.taskmanagement.repository;

import com.paninmiroslav.taskmanagement.entity.PaninMiroslavTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaninMiroslavTaskRepository extends JpaRepository<PaninMiroslavTask, Long> {
}