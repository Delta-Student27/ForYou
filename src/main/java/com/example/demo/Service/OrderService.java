package com.example.demo.Service;

import java.util.List;

import com.example.demo.model.Order;

public interface OrderService {

    Order placeOrder(String email);

    List<Order> getOrdersByUser(String email);
}
