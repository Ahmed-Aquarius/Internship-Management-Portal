package com.example.internship_portal.controller;

import com.example.internship_portal.dto.SubmisionDto;
import com.example.internship_portal.service.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/submissions")
@RequiredArgsConstructor

public class SubmissionController {
    private final SubmissionService service;

    @PostMapping
    public ResponseEntity<SubmisionDto.Response> create(@Valid @RequestBody SubmisionDto.CreateRequest req){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @GetMapping
    public Page<SubmisionDto.Response> listByTask(
            @RequestParam Long taskId, Pageable pageable){
        return service.listByTask(taskId, pageable);
    }

    @PatchMapping("/{id}/feedback")
    public SubmisionDto.Response feedback(
            @PathVariable Long id, @Valid @RequestBody SubmisionDto.FeedbackRequest req){
        return service.giveFeedback(id,req);
}

}
