package com.example.internship_portal.dto;

import jakarta.validation.constraints.*;

public class RoleDto {

    public static record CreateRequest(
            @NotBlank String name
    ) { }

    public static record Response(
            Long id,
            String name
){}
}