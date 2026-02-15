package com.example.demo.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public PaymentServiceImpl(OrderRepository orderRepository,
                              UserRepository userRepository,
                              EmailService emailService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public String processPayment(Long orderId, String email) {

        // ✅ Find user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Find order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // ✅ Check ownership
        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized payment attempt");
        }

        // ✅ Update order status using ENUM
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);

        // ✅ Send Payment Success Email
        try {
            emailService.sendEmail(
                    user.getEmail(),
                    "Payment Successful ✅",
                    "Hi " + user.getName() + ",\n\n"
                            + "Your payment was successful.\n\n"
                            + "Order ID: " + order.getId() + "\n"
                            + "Amount: ₹" + order.getTotalAmount() + "\n\n"
                            + "Thank you for shopping with us!"
            );
        } catch (Exception e) {
            System.out.println("Email failed but payment processed.");
        }

        return "Payment successful for Order #" + orderId;
    }
}
