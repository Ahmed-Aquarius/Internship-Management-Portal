package com.example.internship_portal.service;

import com.example.internship_portal.dto.applicationDto;
import com.example.internship_portal.entity.users.User;
import com.example.internship_portal.exception.BadRequestException;
import com.example.internship_portal.exception.ResourceNotFoundException;
import com.example.internship_portal.entity.*;
import com.example.internship_portal.repository.ApplicationRepository;
import com.example.internship_portal.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository appRepo;
    private final UserRepository userRepo;
    private final InternshipRepository internshipRepo;

    @Override
    public applicationDto.Response apply(applicationDto.CreateRequest req) {
        //add check that internship is open before apply
        //and add check that the same intern not apply for the same internship twice
        User intern = userRepo.findById(req.internId())
                .orElseThrow(() -> new ResourceNotFoundException( "Intern not found"));
        Internship internship = internshipRepo.findById(req.internshipId())
                .orElseThrow(() -> new ResourceNotFoundException( "Internship not found"));

        if(internship.getStatus()!= Internship.InternshipStatus.OPEN){
            throw new BadRequestException("Internship is not open");
        }
        if(appRepo.existsByInternIdAndInternshipId(req.internId(), req.internshipId())) {
            throw new BadRequestException("Internship already exists");
        }
        Application a = new Application();
        a.setIntern(intern);
        a.setInternship(internship);
        a.setCoverLetter(req.coverLetter());
        a.setResumeUrl(req.resumeUrl());
        a.setStatus(Application.ApplicationStatus.PENDING);
        a.setIsActive(true);

        return toDto(appRepo.save(a));
    }

    @Override
    public Page<applicationDto.Response> listForIntern(Long internId, Pageable pageable){
        return appRepo.findByInternId(internId, pageable).map(this::toDto);
    }

    @Override
    public Page<applicationDto.Response> listForInternship(Long internshipId, Pageable pageable){
        return appRepo.findByInternshipId(internshipId, pageable).map(this::toDto);
    }

    @Override
    public applicationDto.Response updateStatus(Long id, applicationDto.UpdateRequest req){
        Application a = appRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        if (req.status() != null) {
            try {
                a.setStatus(Application.ApplicationStatus.valueOf(req.status()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid status");
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