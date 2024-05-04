package com.ms.payment.services;

import com.ms.payment.models.Payment;
import com.ms.payment.models.dto.OperationRequestDTO;
import com.ms.payment.models.dto.PaymentRequestDTO;
import com.ms.payment.models.dto.Status;
import com.ms.payment.repositories.IPaymentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService  {
    IPaymentRepository paymentRepository;

    public PaymentService(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(PaymentRequestDTO payment) {
        Payment newPayment = new Payment();
        BeanUtils.copyProperties(payment, newPayment);

        return paymentRepository.save(newPayment);
    }

    public Payment validateOperation(UUID payer, OperationRequestDTO operation) {
        var payment = this.paymentRepository.findById(operation.id());

        if (payment.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Boleto inválido.");

        System.out.println(payment.get().getStatus().equals(Status.COMPLETED));
        if (payment.get().getStatus().equals(Status.COMPLETED))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Boleto já está pago.");

        return payment.get();
    }

    public Payment executeOperation(Payment payment) {
        payment.setStatus(Status.COMPLETED);
        payment.setUpdateAt(LocalDateTime.now());
        payment.setPaidAt(LocalDateTime.now());

        return this.paymentRepository.save(payment);
    }

    public List<Payment> findAll(){
        return this.paymentRepository.findAll();
    }
}
