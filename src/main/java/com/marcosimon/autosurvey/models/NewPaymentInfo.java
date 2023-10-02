package com.marcosimon.autosurvey.models;

public record NewPaymentInfo(
        //PaymentInfoDTO payInfo,
        String paymentId,
        Double basicSalary,
        Double TGC,
        Double monthlyAllowance

        ) {
}
