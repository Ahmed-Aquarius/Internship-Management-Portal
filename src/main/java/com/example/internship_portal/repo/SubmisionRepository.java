package com.example.internship_portal.repo;

import com.example.internship_portal.model.Submission;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmisionRepository extends JpaRepository<Submission,Long> {
    Page<Submission> findByTaskId(Long taskId, Pageable pageable);

}
