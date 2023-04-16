package com.example.workwave.services;


import com.example.workwave.entities.Role;
import com.example.workwave.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl {

    @Autowired
    RoleRepository roleRepository;
    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }


}
