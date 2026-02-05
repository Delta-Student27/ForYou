package com.example.demo.Service.Impl;
import com.example.demo.Service.OrderService;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(CartRepository cartRepository,
                            OrderRepository orderRepository,
                            UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order placeOrder(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Cart cart = cartRepository.findByUser(user).orElseThrow();

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return orderRepository.findByUser(user);
    }
}
