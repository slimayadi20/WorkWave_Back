package com.example.workwave.services;

import com.example.workwave.entities.Invoices;
import com.example.workwave.entities.Order;
import com.example.workwave.entities.User;
import com.example.workwave.entities.products;
import com.example.workwave.repositories.*;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository OrderRepository;

    @Autowired
    ServletContext context;
    @Autowired
    productsRepository productsRepository;
    @Autowired
    InvoicesRepository invoicesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    RoleRepository roleRepository;

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
    public void addInvoice(Long o_id,String userName){
        Invoices invoices = new Invoices();
        invoices.setIssueDate(LocalDate.now());
        invoices.setInvoiceNumber("ORDER-"+o_id);
        invoices.setStatus("To Pay");
        invoices.setOrder(OrderRepository.findById(o_id).get());
        invoices.setDescription("Invoice for Order Number "+o_id);
        BigDecimal amount = BigDecimal.valueOf(Integer.valueOf(OrderRepository.findById(o_id).get().getQuantity()) * productsRepository.getProductPricesByOrderId(o_id) );
        invoices.setAmountDue(amount);
        invoices.setCreatedBy(userName);
        invoices.setBankAccount(bankAccountRepository.findByUserUserName(String.valueOf(userRepository.findById(userRepository.findByRole(roleRepository.findRoleByRoleName("Project manager"), (Pageable) userRepository.findAllById(Collections.singleton(userName))).toString()))));
        invoicesRepository.save(invoices);
    }

}