package com.example.workwave.controllers;

import com.example.workwave.entities.products;
import com.example.workwave.repositories.productsRepository;
import com.example.workwave.services.productsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productsController {

    @Autowired
    productsRepository productsRepository;



    @Autowired
    productsServiceImpl productsservice;

    @PostMapping("/addproducts")
    public String addproducts(@RequestBody products products ) {

        return productsservice.addproducts(products);

    }
    @DeleteMapping("/deleteproducts/{p_id}")
    public String deleteDepotproducts(@PathVariable long p_id) {

        return productsservice.deleteproducts(p_id);
    }
    @PutMapping("/updateproducts")
    public products updateproducts(@RequestBody products products) {

        return productsservice.updateproducts(products);
    }
    @GetMapping("/products")//affichage+pagination
    public List<products> show() {
        return productsservice.GetAllproducts();

    }
















}
