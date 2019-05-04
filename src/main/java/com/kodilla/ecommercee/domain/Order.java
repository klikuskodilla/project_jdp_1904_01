package com.kodilla.ecommercee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Date dateCreated;
    private String status;
    private List<Product> orderProducts = new ArrayList<>();

    public Order() {
    }

    public Order(Long id, String status) {
        this.id = id;
        dateCreated = new Date();
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }
}
