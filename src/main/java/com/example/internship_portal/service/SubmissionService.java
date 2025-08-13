package com.example.internship_portal.service;

import com.example.internship_portal.dto.SubmisionDto;
import com.example.internship_portal.model.Submission;
import com.example.internship_portal.model.Task;
import com.example.internship_portal.model.User;
import com.example.internship_portal.repo.SubmisionRepository;
import com.example.internship_portal.repo.TaskRepository;
import com.example.internship_portal.repo.UserRepository;
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
    private final TaskRepository taskRepo;
    private final UserRepository userRepo;



    public SubmisionDto.Response create(SubmisionDto.CreateRequest req){
        Task task = taskRepo.findById(req.taskId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        User intern = userRepo.findById(req.internId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Intern not found"));

        Submission s = new Submission();
        s.setTask(task);
        s.setIntern(intern);
        s.setContent(req.content());
        s.setStatus(Submission.SubmissionStatus.SUBMITTED);
        s.setIsActive(true);
        // submittedAt handled by @PrePersist
        return toDto(submissionRepo.save(s));
    }

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
