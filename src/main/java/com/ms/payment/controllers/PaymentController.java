package com.ms.payment.controllers;

import com.ms.payment.models.Payment;
import com.ms.payment.models.dto.AuthenticationBodyDTO;
import com.ms.payment.models.dto.OperationRequestDTO;
import com.ms.payment.models.dto.PaymentRequestDTO;
import com.ms.payment.services.PaymentService;
import com.ms.payment.services.UserServiceClient;
import com.ms.payment.utils.ValidationToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    PaymentService paymentService;
    UserServiceClient userServiceClient;

    @Autowired
    public PaymentController(PaymentService paymentService, UserServiceClient userServiceClient) {
        this.paymentService = paymentService;
        this.userServiceClient = userServiceClient;
    }

    @PostMapping("register")
    public ResponseEntity<Payment> register(@RequestBody PaymentRequestDTO payment) {
        var newPayment = this.paymentService.createPayment(payment);

        return ResponseEntity.ok().body(newPayment);
    }

    @PatchMapping("/operation")
    public ResponseEntity<Payment> operation(
            HttpServletRequest request,
            @RequestBody OperationRequestDTO operation) {
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println(authorizationHeader);

        var user = this.userServiceClient.validate(authorizationHeader);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var order = this.paymentService.validateOperation(UUID.fromString(user), operation);

        var orderComplete = this.paymentService.executeOperation(order);

        return ResponseEntity.ok().body(orderComplete);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getPayments() {
        var listPayments = this.paymentService.findAll();

        return ResponseEntity.ok().body(listPayments);
    }
}
