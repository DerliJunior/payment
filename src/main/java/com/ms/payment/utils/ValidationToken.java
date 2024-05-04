package com.ms.payment.utils;

import com.ms.payment.models.dto.AuthenticationBodyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class ValidationToken {

    public static AuthenticationBodyDTO validate(String authorizationHeader){
        if (authorizationHeader == null || !(authorizationHeader.startsWith("Bearer ")))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Authorization token");

        return new AuthenticationBodyDTO(authorizationHeader.substring(7));
    }
}
