package com.example.internship_portal.controller;

import com.example.internship_portal.dto.applicationDto;
import com.example.internship_portal.service.ApplicationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationServiceImpl service;

    // Intern applies
    @PostMapping
    public ResponseEntity<applicationDto.Response> apply(@Valid @RequestBody applicationDto.CreateRequest req){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.apply(req));
    }

    // List an intern's applications
    @GetMapping("/by-intern/{internId}")
    public Page<applicationDto.Response> listForIntern(@PathVariable Long internId, Pageable pageable){
        return service.listForIntern(internId, pageable);
    }

    // List applications for a specific internship (mentor/admin review queue)
    @GetMapping("/by-internship/{internshipId}")
    public Page<applicationDto.Response> listForInternship(@PathVariable Long internshipId, Pageable pageable){
        return service.listForInternship(internshipId, pageable);
    }

    // Mentor/Admin accepts or rejects
    @PatchMapping("/{id}/status")
    public applicationDto.Response updateStatus(@PathVariable Long id, @RequestBody applicationDto.UpdateRequest req){
        return service.updateStatus(id, req);
}
}