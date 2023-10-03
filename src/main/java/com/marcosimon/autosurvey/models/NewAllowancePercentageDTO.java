package com.marcosimon.autosurvey.models;

public record NewAllowancePercentageDTO(
        //AllowancePercentageDTO allPerc,
        String allowancePercentageId,
        Float COLA,
        Float transportation,
        Float housing,
        Float other,
        Float total
) {
}
