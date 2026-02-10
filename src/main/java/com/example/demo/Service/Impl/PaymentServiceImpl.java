
package com.example.demo.Service.Impl;
import com.example.demo.Service.PaymentService;


import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String createPayment(Double amount) {
        // Razorpay integration will come here
        return "PAYMENT_ORDER_CREATED";
    }
}
