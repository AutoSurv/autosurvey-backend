package com.marcosimon.autosurvey.models;

public record CreateUserDto(
        String email,
        String password
) {
}
