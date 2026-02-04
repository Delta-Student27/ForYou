package com.example.demo.Service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

    User register(User user);

    User getByEmail(String email);

    List<User> getAllUsers();
}
