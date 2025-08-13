package com.example.internship_portal.repo;

import com.example.internship_portal.model.Application;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository <Application,Long> {
    Page<Application> findByInternId(Long internId , Pageable pageable);
    Page<Application> findByInternshipId(Long internshipId , Pageable pageable);
}
