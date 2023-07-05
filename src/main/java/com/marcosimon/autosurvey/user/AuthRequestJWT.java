package com.marcosimon.autosurvey.user;

public record AuthRequestJWT(
        String username,
        String password
) {
}