package com.example.internship_portal.service;

import com.example.internship_portal.dto.applicationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationService {
    applicationDto.Response apply(applicationDto.CreateRequest req);
    Page<applicationDto.Response> listForIntern(Long internId, Pageable pageable);
    Page<applicationDto.Response> listForInternship(Long internshipId, Pageable pageable);
    applicationDto.Response updateStatus(Long id, applicationDto.UpdateRequest req);
}
