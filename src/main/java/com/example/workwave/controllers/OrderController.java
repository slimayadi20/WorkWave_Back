package com.example.workwave.controllers;

import com.example.workwave.entities.Order;
import com.example.workwave.entities.Supplier;
import com.example.workwave.entities.products;
import com.example.workwave.repositories.OrderRepository;
import com.example.workwave.repositories.SupplierRepository;
import com.example.workwave.repositories.productsRepository;
import com.example.workwave.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@RestController
public class OrderController {

    @Autowired
    OrderRepository OrderRepository;

    @Autowired
    OrderServiceImpl Orderservice;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    productsRepository productsRepository;



    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order) {
        Set<products> products = order.getProduct();
        if (products != null && !products.isEmpty()) {
            products product = products.iterator().next();
            long p_id = product.getP_id();
            System.out.println("p_id");
            System.out.println(p_id);
            // do something with p_id
            Orderservice.addOrder(order);
            Orderservice.addOrderToProduct(order.getO_id(), p_id);

        }


        return "ok";

    }

    @DeleteMapping("/deleteOrder/{o_id}")
    public String deleteOrder(@PathVariable long o_id) {

        return Orderservice.deleteOrder(o_id);
    }

    @PutMapping("/updateOrder")
    public Order updateOrder(@RequestBody Order Order) {

        return Orderservice.updateOrder(Order);
    }

    @GetMapping("/Order")//affichage+pagination
    public List<Order> show() {
        return Orderservice.GetAllOrder();

    }

    @GetMapping(path = "/getOrder/{Order}")
    public Order getorder(@PathVariable("Order") long o_id) throws Exception {
        return Orderservice.getorder(o_id);
    }


}