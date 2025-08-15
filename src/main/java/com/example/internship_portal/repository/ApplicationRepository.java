package com.example.internship_portal.repository;

import com.example.internship_portal.entity.Application;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository <Application,Long> {
    Page<Application> findByInternId(Long internId , Pageable pageable);
    Page<Application> findByInternshipId(Long internshipId , Pageable pageable);
    boolean existsByInternIdAndInternshipId(Long internId, Long internshipId);
    //long countByInternshipIdAndStatus(Long internshipId, Application.ApplicationStatus.status);
}
