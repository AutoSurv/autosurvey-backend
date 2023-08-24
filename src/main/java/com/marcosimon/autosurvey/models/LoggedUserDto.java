package com.marcosimon.autosurvey.models;

public record LoggedUserDto(
        String username,
        String email,
        String role,
        String status,
        String token
) {
}
