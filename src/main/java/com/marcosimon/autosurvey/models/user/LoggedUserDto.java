package com.marcosimon.autosurvey.models.user;

public record LoggedUserDto(
        String userId,
        String username,
        String email,
        String role,
        String status,
        String token
) {
}
