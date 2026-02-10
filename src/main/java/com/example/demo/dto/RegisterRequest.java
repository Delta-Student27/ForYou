package com.example.demo.dto;

import com.example.demo.model.RoleName;

public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private RoleName role;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleName getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }
}
