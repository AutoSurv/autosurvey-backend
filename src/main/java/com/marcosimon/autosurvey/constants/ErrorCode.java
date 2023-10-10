package com.marcosimon.autosurvey.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST Wrong Request from param
    INVALID_PARAMETER(400, "check your parameter value"),

    //404 NOT_FOUND  Wrong Resource Access
    FUNCTION_INFO_NOT_FOUND(404, "Function info ID not exist"),
    FUNCTION_SALARY_INFO_NOT_FOUND(404, "Function Salary info ID not exist"),
    ALLOWANCE_PERCENT_INFO_NOT_FOUND(404, "Allowance Percent info ID not exist"),
    ALLOWANCE_INFO_NOT_FOUND(404, "Allowance info ID not exist"),
    CURRENCY_INFO_NOT_FOUND(404, "Currency info ID not exist"),
    COUNTRY_INFO_NOT_FOUND(404, "Country info ID not exist"),
    CONTACT_INFO_NOT_FOUND(404, "Contact info ID not exist"),
    ORG_INFO_NOT_FOUND(404, "Organization info ID not exist"),
    SURVEY_NOT_FOUND(404, "Survey ID not exist"),
    ORGANIZATION_NOT_FOUND(404, "Organization ID not exist"),
    USER_NOT_FOUND(404, "User ID not exist"),

    SAVED_SURVEY_NOT_FOUND(404, "Survey is not in DB"),
    SAVED_ORGANIZATION_NOT_FOUND(404, "Organization is not in DB"),
    SAVED_USER_NOT_FOUND(404, "User is not in DB"),

    //409 CONFLICT Duplicate Resource
    ALREADY_SAVED_FUNCTION_INFO(409, "Function info is already saved"),
    ALREADY_SAVED_FUNCTION_SALARY_INFO(409, "Function Salary info is already saved"),
    ALREADY_SAVED_ALLOWANCE_PERCENT_INFO(409, "Allowance Percent info is already saved"),
    ALREADY_SAVED_ALLOWANCE_INFO(409, "Allowance info is already saved"),
    ALREADY_SAVED_CURRENCY_INFO(409, "Currency info is already saved"),
    ALREADY_SAVED_COUNTRY_INFO(409, "Country info is already saved"),
    ALREADY_SAVED_CONTACT_INFO(409, "Contact info is already saved"),
    ALREADY_SAVED_SURVEY(409, "Survey is already saved"),
    ALREADY_SAVED_ORGANIZATION(409, "Organization is already saved"),
    ALREADY_SAVED_USER(409, "User is already saved"),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "Server Error. Contact Server Team");

    private final int status;
    private final String message;

}
