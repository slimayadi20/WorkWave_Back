package com.example.workwave.controllers;


import com.example.workwave.entities.Role;
import com.example.workwave.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {

        return roleService.createNewRole(role);

    }



}
