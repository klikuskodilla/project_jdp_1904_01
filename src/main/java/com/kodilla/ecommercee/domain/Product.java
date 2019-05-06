package com.kodilla.ecommercee.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {
    private Order order;

    @ManyToOne
    @JoinColumn(name = "Order_Id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
