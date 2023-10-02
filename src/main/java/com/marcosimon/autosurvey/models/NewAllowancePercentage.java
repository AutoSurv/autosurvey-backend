package com.marcosimon.autosurvey.models;

public record NewAllowancePercentage(
        //AllowancePercentageDTO allPerc,
        String allowancePercentageId,
        Float COLA,
        Float transportation,
        Float housing,
        Float other,
        Float total
) {
}
