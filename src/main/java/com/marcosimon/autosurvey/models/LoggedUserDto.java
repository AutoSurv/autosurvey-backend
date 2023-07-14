package com.marcosimon.autosurvey.models;

public record LoggedUserDto(
        String username,
        String role,
        String token
) {
}
