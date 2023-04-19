package com.example.workwave.services;

import com.example.workwave.entities.products;
import com.example.workwave.repositories.productsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;

@Service
public class productsServiceImpl {

    @Autowired
    productsRepository productsRepository;

    //@Autowired
    //ServletContext context;

    public List<products> GetAllproducts() {
        return productsRepository.findAll();
    }

    public String addproducts (products p){
        productsRepository.save(p);
        return"ok" ;
    }

    public String deleteproducts(long p_id) {
        productsRepository.deleteById(p_id);
        return "product removed !!" +p_id;
    }

    public products updateproducts(products products) {
     /*   products existingproducts = productsRepository.findById(products.getP_id()).orElse(null);
       existingproducts.setName(products.getName());
        existingproducts.setPrice(products.getPrice());
        existingproducts.setQuantity(products.getQuantity());

*/
        return productsRepository.save(products);
    }



}
