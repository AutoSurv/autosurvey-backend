package com.marcosimon.autosurvey.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST Wrong Request from param
    INVALID_PARAMETER(400, "check your parameter value"),

    //404 NOT_FOUND  Wrong Resource Access
    SURVEY_NOT_FOUND(404, "Survey ID not exist"),
    ORGANIZATION_NOT_FOUND(404, "Organization ID not exist"),
    USER_NOT_FOUND(404, "User ID not exist"),
    SAVED_SURVEY_NOT_FOUND(404, "Survey is not in DB"),
    SAVED_ORGANIZATION_NOT_FOUND(404, "Organization is not in DB"),
    SAVED_USER_NOT_FOUND(404, "User is not in DB"),


    //409 CONFLICT Duplicate Resource
    ALREADY_SAVED_SURVEY(409, "Survey is already saved"),
    ALREADY_SAVED_ORGANIZATION(409, "Organization is already saved"),
    ALREADY_SAVED_USER(409, "User is already saved"),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "Server Error. Contact Server Team");

    private final int status;
    private final String message;

}
