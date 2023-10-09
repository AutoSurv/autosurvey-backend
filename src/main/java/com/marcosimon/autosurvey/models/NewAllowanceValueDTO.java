package com.marcosimon.autosurvey.models;

public record NewAllowanceValueDTO(
        //AllowanceValueDTO allValue
        String allowanceId,
        Integer COLA,
        Integer transportation,
        Integer housing,
        Integer other,
        Integer total
) {
}
