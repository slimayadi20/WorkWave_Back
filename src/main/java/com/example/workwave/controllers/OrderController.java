package com.example.workwave.controllers;

import com.example.workwave.entities.Invoices;
import com.example.workwave.entities.Order;
import com.example.workwave.entities.products;
import com.example.workwave.repositories.InvoicesRepository;
import com.example.workwave.repositories.OrderRepository;
import com.example.workwave.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
public class OrderController {

    @Autowired
    OrderRepository OrderRepository;

    @Autowired
    OrderServiceImpl Orderservice;

    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order, @RequestParam String userName) {
        Orderservice.addOrder(order);

        Set<products> products = order.getProduct();
        if (products != null && !products.isEmpty()) {
            products product = products.iterator().next();
            long p_id = product.getP_id();
            // do something with p_id
            Orderservice.addOrderToProduct(order.getO_id() , p_id);
            Orderservice.addInvoice(order.getO_id(), userName);
        }
        return  "ok";
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








}