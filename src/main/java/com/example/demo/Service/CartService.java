package com.example.demo.service;

import com.example.demo.model.Cart;

public interface CartService {

    Cart getCartByUserEmail(String email);

    Cart addToCart(String email, Long productId, int quantity);
}
