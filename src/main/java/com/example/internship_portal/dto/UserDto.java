package com.example.internship_portal.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class UserDto {
    public static record CreateRequest(
            @NotBlank @Size(min = 3, max = 50) String username,
            @NotBlank String password,
            @NotBlank @Email String email,
            @NotBlank @Size(max = 100) String fullName,
            List<String> roles
    ) { }

    public static record UpdateRequest(
            String email,
            String fullName,
            Boolean isActive,
            List<String> roles
    ) { }

    public static record Response(
            Long id,
            String username,
            String email,
            String fullName,
            Boolean isActive,
            List<String> roles,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
){}

}
