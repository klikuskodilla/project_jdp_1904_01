package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "Id", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "Date_Created")
    @NotNull
    public Date getDateCreated() {
        return dateCreated;
    }

    @Column(name = "Status")
    @NotNull
    public String getStatus() {
        return status;
    }

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    private void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
