package com.ms.payment.models.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record OperationRequestDTO (
        UUID id
){
    @Override
    public UUID id() {
        return id;
    }
}