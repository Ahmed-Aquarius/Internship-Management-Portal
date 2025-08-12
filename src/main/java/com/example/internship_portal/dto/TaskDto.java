package com.example.internship_portal.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class TaskDto {
    public static record CreateRequest(
            @NotBlank String title,
            String description,
            @NotNull @FutureOrPresent LocalDate deadline,
            @NotNull Long internshipId
    ) { }

    public static record UpdateRequest(
            String title,
            String description,
            @FutureOrPresent LocalDate deadline,
            Boolean isActive
    ) { }

    public static record Response(
            Long id,
            String title,
            String description,
            LocalDate deadline,
            Boolean isActive,
            Long internshipId
    ){}

}
