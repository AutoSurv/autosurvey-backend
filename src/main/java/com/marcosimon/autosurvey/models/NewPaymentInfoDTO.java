package com.marcosimon.autosurvey.models;

public record NewPaymentInfoDTO(
        //PaymentInfoDTO payInfo,
        Long paymentId,
        Double basicSalary,
        Double TGC,
        Double monthlyAllowance

        ) {
}
