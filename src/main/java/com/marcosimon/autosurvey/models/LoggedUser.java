package com.marcosimon.autosurvey.models;

public record LoggedUser(
        String username,
        String role,
        String token
) {
}
