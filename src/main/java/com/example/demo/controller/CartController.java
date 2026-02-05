


package com.example.demo.Controller;

import com.example.demo.model.Cart;
import com.example.demo.Service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestParam String email,
                          @RequestParam Long productId,
                          @RequestParam int quantity) {
        return cartService.addToCart(email, productId, quantity);
    }
}
