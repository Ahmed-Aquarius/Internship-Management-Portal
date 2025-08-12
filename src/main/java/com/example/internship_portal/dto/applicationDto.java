package com.example.internship_portal.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class applicationDto {
    public static record CreateRequest(
            @NotNull Long internId,
            @NotNull Long internshipId,
            String coverLetter,
            String resumeUrl
    ) { }

    public static record UpdateRequest(
            String status,
            Boolean isActive
    ) { }

    public static record Response(
            Long id,
            Long internId,
            Long internshipId,
            String status,
            String coverLetter,
            String resumeUrl,
            Boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
){}

}
