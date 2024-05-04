package com.ms.payment.services;

import com.ms.payment.models.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Service
@FeignClient(name = "ms-user", url = "http://localhost:3333/autentication")
public interface UserServiceClient {

    @GetMapping("/")
    ArrayList<UserResponseDTO> getAccounts();

    @PostMapping("/validate")
    String validate(
            @RequestHeader("Authorization") String token);
}
