package com.example.internship_portal.repository;

import com.example.internship_portal.entity.Task;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Page<Task> findByInternshipId(Long internshipId, Pageable pageable);
    Page<Task> findByIsActiveTrue(Pageable pageable);

}
