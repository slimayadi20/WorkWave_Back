package com.example.workwave.controllers;


import com.example.workwave.entities.JwtRequest;
import com.example.workwave.entities.JwtResponse;
import com.example.workwave.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println("request");
        System.out.println(jwtRequest.getUserName());
        System.out.println(jwtRequest.getPassword());
        return jwtService.createJwtToken(jwtRequest);
    }


}
