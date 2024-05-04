package com.ms.payment.models.dto;

import java.util.Date;
import java.util.UUID;

public record PaymentRequestDTO(UUID payer, double value, Date dueDate) {

    @Override
    public UUID payer() {
        return payer;
    }

    @Override
    public double value() {
        return value;
    }

    @Override
    public Date dueDate() {
        return dueDate;
    }
}
