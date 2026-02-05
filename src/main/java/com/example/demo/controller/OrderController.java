package com.example.demo.Controller;

import com.example.demo.model.Order;
import com.example.demo.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public Order placeOrder(@RequestParam String email) {
        return orderService.placeOrder(email);
    }

    @GetMapping
    public List<Order> getOrders(@RequestParam String email) {
        return orderService.getOrdersByUser(email);
    }
}
