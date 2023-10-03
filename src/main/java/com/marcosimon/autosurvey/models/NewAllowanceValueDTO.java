package com.marcosimon.autosurvey.models;

public record NewAllowanceValueDTO(
        //AllowanceValueDTO allValue
        String allowancePercentageId,
        Float COLA,
        Float transportation,
        Float housing,
        Float other,
        Float total
) {
}
