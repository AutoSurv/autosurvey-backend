package com.marcosimon.autosurvey.models;

public record UserDto(
        String userId,
        String email,
        String accessToken
) {
}
