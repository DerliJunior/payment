package com.ms.payment.models.dto;

public class AuthenticationBodyDTO{
    private String token;

    public AuthenticationBodyDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
