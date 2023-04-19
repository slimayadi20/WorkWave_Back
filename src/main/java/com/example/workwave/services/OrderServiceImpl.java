package com.example.workwave.services;

import com.example.workwave.entities.Order;
import com.example.workwave.entities.products;
import com.example.workwave.repositories.OrderRepository;
import com.example.workwave.repositories.productsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;
@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository OrderRepository;

    @Autowired
    ServletContext context;
    @Autowired
    productsRepository productsRepository;

    public List<Order> GetAllOrder() {
        return OrderRepository.findAll();
    }

    public String addOrder (Order o){
        OrderRepository.save(o);
        return"ok" ;
    }

    public String deleteOrder(long o_id) {
        OrderRepository.deleteById(o_id);
        return "Order removed !!" +o_id;
    }

    public Order updateOrder(Order Order) {
        Order existingOrder = OrderRepository.findById(Order.getO_id()).orElse(null);
        existingOrder.setQuantity(Order.getQuantity());
        existingOrder.setOrderDate(Order.getOrderDate());
        //existingDepot.setDepotCapacity(Order.getDepotCapacity());


        return OrderRepository.save(existingOrder);
    }
    public void addOrderToProduct(long o_id, long p_id) {
        Order order = OrderRepository.findById(o_id).orElse(null);
        products product = productsRepository.findById(p_id).orElse(null);
        if (order != null && product != null) {
            product.setOrder(order);
            productsRepository.save(product);
        }
    }

}
