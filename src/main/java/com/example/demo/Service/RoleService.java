package com.example.demo.Service;

import com.example.demo.Repository.RoleRepo;
import com.example.demo.model.Role;
import com.example.demo.model.RoleName;

import org.springframework.stereotype.Service;


@Service

public class RoleService {
    private final RoleRepo roleRepo;
    
    
    
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
        
    }
    public Role getRoleByName(RoleName roleName) {
        return roleRepo.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
    }
    public Role createRole(RoleName roleName) {
        if (roleRepo.existsByName(roleName)) {
            throw new RuntimeException("Role already exists: " + roleName);
        }
        Role role = new Role(roleName);
        return roleRepo.save(role);
    }
}
