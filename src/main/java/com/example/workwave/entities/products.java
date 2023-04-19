package com.example.workwave.entities;

import javax.persistence.*;

@Entity
public class products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long p_id;
    private String name;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="o_id", nullable=true)
    private Order order;

    public products() {
    }

    public products(long p_id, String name, double price, int quantity, Order order) {
        this.p_id = p_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
    }

    public long getP_id() {
        return p_id;
    }

    public void setP_id(long p_id) {
        this.p_id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
