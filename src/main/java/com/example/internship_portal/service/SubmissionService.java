package com.example.internship_portal.service;

import com.example.internship_portal.dto.SubmisionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubmissionService {
    SubmisionDto.Response create(SubmisionDto.CreateRequest req);
    Page<SubmisionDto.Response> listByTask(Long taskId, Pageable pageable);
    SubmisionDto.Response giveFeedback(Long submissionId, SubmisionDto.FeedbackRequest req);
}
