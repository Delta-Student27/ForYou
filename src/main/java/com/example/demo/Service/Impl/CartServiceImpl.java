
package com.example.demo.Service.Impl;

import com.example.demo.Service.CartService;



import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cart getCartByUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(user)));
    }

    @Override
    public Cart addToCart(String email, Long productId, int quantity) {
        Cart cart = getCartByUserEmail(email);
        Product product = productRepository.findById(productId).orElseThrow();

        Optional<CartItem> existingItem =
                cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem item = new CartItem(cart, product, quantity);
            cartItemRepository.save(item);
        }
        return cart;
    }
}
