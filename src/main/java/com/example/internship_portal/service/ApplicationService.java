package com.example.internship_portal.service;

import com.example.internship_portal.dto.applicationDto;
import com.example.internship_portal.model.*;
import com.example.internship_portal.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {
    private final ApplicationRepository appRepo;
    private final UserRepository userRepo;
    private final InternshipRepository internshipRepo;

    public applicationDto.Response apply(applicationDto.CreateRequest req) {
        User intern = userRepo.findById(req.internId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Intern not found"));
        Internship internship = internshipRepo.findById(req.internshipId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Internship not found"));

        Application a = new Application();
        a.setIntern(intern);
        a.setInternship(internship);
        a.setCoverLetter(req.coverLetter());
        a.setResumeUrl(req.resumeUrl());
        a.setStatus(Application.ApplicationStatus.PENDING);
        a.setIsActive(true);

        return toDto(appRepo.save(a));
    }

    public Page<applicationDto.Response> listForIntern(Long internId, Pageable pageable){
        return appRepo.findByInternId(internId, pageable).map(this::toDto);
    }

    public Page<applicationDto.Response> listForInternship(Long internshipId, Pageable pageable){
        return appRepo.findByInternshipId(internshipId, pageable).map(this::toDto);
    }

    public applicationDto.Response updateStatus(Long id, applicationDto.UpdateRequest req){
        Application a = appRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        if (req.status() != null) {
            try {
                a.setStatus(Application.ApplicationStatus.valueOf(req.status()));
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status");
            }
        }
        if (req.isActive()!=null) a.setIsActive(req.isActive());
        return toDto(appRepo.save(a));
    }

    private applicationDto.Response toDto(Application a){
        return new applicationDto.Response(
                a.getId(),
                a.getIntern().getId(),
                a.getInternship().getId(),
                a.getStatus().name(),
                a.getCoverLetter(),
                a.getResumeUrl(),
                a.getIsActive(),
                a.getCreatedAt(),
                a.getUpdatedAt()
                );
}
}