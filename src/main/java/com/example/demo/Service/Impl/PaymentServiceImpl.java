package com.example.demo.service.Impl;
import com.example.demo.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String createPayment(Double amount) {
        // Razorpay integration will come here
        return "PAYMENT_ORDER_CREATED";
    }
}
