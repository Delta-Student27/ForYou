package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public String makePayment(@RequestParam Long orderId) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return paymentService.processPayment(orderId, email);
    }
}
