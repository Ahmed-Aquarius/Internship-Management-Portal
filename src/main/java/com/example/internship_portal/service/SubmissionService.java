package com.example.internship_portal.service;

import com.example.internship_portal.dto.SubmisionDto;
import com.example.internship_portal.model.Submission;
import com.example.internship_portal.repo.SubmisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class SubmissionService {
    private final SubmisionRepository submissionRepo;

    public Page<SubmisionDto.Response> listByTask(Long taskId, Pageable pageable){
        return submissionRepo.findByTaskId(taskId, pageable).map(this::toDto);
    }

    // mentor/admin reviews a submission
    public SubmisionDto.Response giveFeedback(Long submissionId, SubmisionDto.FeedbackRequest req){
        var s = submissionRepo.findById(submissionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Submission not found"));
        s.setFeedback(req.feedback());
        s.setScore(req.score());
        if (req.status()!=null) {
            try {
                s.setStatus(Submission.SubmissionStatus.valueOf(req.status()));
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status");
            }
        }
        s.setReviewedAt(LocalDateTime.now());
        return toDto(submissionRepo.save(s));
    }
    private SubmisionDto.Response toDto(Submission s){
        return new SubmisionDto.Response(
                s.getId(),
                s.getTask().getId(),
                s.getIntern().getId(),
                s.getContent(),
                s.getStatus().name(),
                s.getScore(),
                s.getFeedback(),
                s.getSubmittedAt(),
                s.getReviewedAt(),
                s.getIsActive()
                );
}

}
