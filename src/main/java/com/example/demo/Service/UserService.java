package com.example.demo.Service;

import java.util.HashSet;

import org.springframework.stereotype.Service;
import com.example.demo.Repository.UserRepo;
import com.example.demo.model.User;
import com.example.demo.model.RoleName;
import com.example.demo.model.Role;
import java.util.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service    

public class UserService {

    private final UserRepo userRepo;
    private final RoleService roleService;
    public UserService(UserRepo userRepo, RoleService roleService) {
        this.userRepo = userRepo;
        this.roleService = roleService;
    }
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public User registerUser(String email, String password) {
        if (userRepo.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        Role userRole = roleService.getRoleByName(RoleName.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        return userRepo.save(user);
    }
}
