package com.example.demo.Controller;

import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User savedUser = userService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        try{
        String email = request.get("email");
        String password = request.get("password");

        // 1. Fetch user from DB
        User user = userService.getByEmail(email);

        // 2. Check password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        }

        // 3. Generate JWT
        String token = jwtUtil.generateToken(email);

        return ResponseEntity.ok(Map.of("token", token));
        }catch (Exception e){
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        }
    }
}

