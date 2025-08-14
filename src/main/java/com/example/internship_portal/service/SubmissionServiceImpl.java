package com.example.internship_portal.service;

import com.example.internship_portal.dto.SubmisionDto;
import com.example.internship_portal.exception.BadRequestException;
import com.example.internship_portal.exception.ResourceNotFoundException;
import com.example.internship_portal.model.Application;
import com.example.internship_portal.model.Submission;
import com.example.internship_portal.model.Task;
import com.example.internship_portal.model.users.User;
import com.example.internship_portal.repo.ApplicationRepository;
import com.example.internship_portal.repo.SubmisionRepository;
import com.example.internship_portal.repo.TaskRepository;
import com.example.internship_portal.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class SubmissionServiceImpl implements SubmissionService {
    private final SubmisionRepository submissionRepo;
    private final TaskRepository taskRepo;
    private final UserRepository userRepo;
    private final ApplicationRepository appRepo;


    @Override
    public SubmisionDto.Response create(SubmisionDto.CreateRequest req){

        Task task = taskRepo.findById(req.taskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        User intern = userRepo.findById(req.internId())
                .orElseThrow(() -> new ResourceNotFoundException("Intern not found"));

        boolean acceptedForThisInternship =
                appRepo.existsByInternIdAndInternshipId(req.internId(), task.getInternship().getId()) &&
                        appRepo.findByInternId(req.internId(), org.springframework.data.domain.PageRequest.of(0, 50))
                                .stream()
                                .anyMatch(a -> a.getInternship().getId().equals(task.getInternship().getId())
                                        && a.getStatus()== Application.ApplicationStatus.ACCEPTED);

        if (!acceptedForThisInternship) {
            throw new BadRequestException("Intern is not accepted for this internship");
        }

        if (task.isOverdue()) {
            throw new BadRequestException("Deadline has passed");
        }

        Submission s = new Submission();
        s.setTask(task);
        s.setIntern(intern);
        s.setContent(req.content());
        s.setStatus(Submission.SubmissionStatus.SUBMITTED);
        s.setIsActive(true);
        // submittedAt handled by @PrePersist
        return toDto(submissionRepo.save(s));
    }

    @Override
    public Page<SubmisionDto.Response> listByTask(Long taskId, Pageable pageable){
        return submissionRepo.findByTaskId(taskId, pageable).map(this::toDto);
    }

    @Override
    public SubmisionDto.Response giveFeedback(Long submissionId, SubmisionDto.FeedbackRequest req){
        var s = submissionRepo.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found"));
        s.setFeedback(req.feedback());
        s.setScore(req.score());
        if (req.status()!=null) {
            try {
                s.setStatus(Submission.SubmissionStatus.valueOf(req.status()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid status");
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
