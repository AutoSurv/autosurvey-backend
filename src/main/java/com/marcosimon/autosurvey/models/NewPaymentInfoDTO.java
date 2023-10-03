package com.marcosimon.autosurvey.models;

public record NewPaymentInfoDTO(
        //PaymentInfoDTO payInfo,
        String paymentId,
        Double basicSalary,
        Double TGC,
        Double monthlyAllowance

        ) {
}
