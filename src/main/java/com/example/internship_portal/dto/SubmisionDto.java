package com.example.internship_portal.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class SubmisionDto {

    public static record CreateRequest(
            @NotNull Long taskId,
            @NotNull Long internId,
            @NotBlank String content
    ) { }

    public static record FeedbackRequest(
            @NotBlank String feedback,
            @Min(0) @Max(100) Integer score,
            String status
    ) { }

    public static record Response(
            Long id,
            Long taskId,
            Long internId,
            String content,
            String status,
            Integer score,
            String feedback,
            LocalDateTime submittedAt,
            LocalDateTime reviewedAt,
            Boolean isActive
){}

}
