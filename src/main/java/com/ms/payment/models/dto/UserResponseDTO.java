package com.ms.payment.models.dto;

public record UserResponseDTO(String name, String email, String password) {

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }
}