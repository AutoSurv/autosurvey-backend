package com.marcosimon.autosurvey.models;

public record LoggedUserDto(
        String userName,
        String role,
        String token
) {
}
