package com.marcosimon.autosurvey.exception;

import lombok.AllArgsConstructor;

public record ErrorDTO(
        int status,
        String message
) {
}
