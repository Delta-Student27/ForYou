package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // ✅ Add product to cart
    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long productId,
                          @RequestParam int quantity) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return cartService.addToCart(email, productId, quantity);
    }

    // ✅ Get logged-in user's cart
    @GetMapping
    public Cart getMyCart() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return cartService.getCartByUserEmail(email);
    }
}
