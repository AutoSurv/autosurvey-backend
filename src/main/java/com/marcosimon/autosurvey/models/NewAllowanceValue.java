package com.marcosimon.autosurvey.models;

public record NewAllowanceValue(
        //AllowanceValueDTO allValue
        String allowancePercentageId,
        Float COLA,
        Float transportation,
        Float housing,
        Float other,
        Float total
) {
}
