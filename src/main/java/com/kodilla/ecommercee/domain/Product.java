package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Products")
public class Product {
    private Long id;
    private String name;
    private double prize;

    public Product() {
    }

    public Product(String name, double prize) {
        this.name = name;
        this.prize = prize;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "Id", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "Name")
    @NotNull
    public String getName() {
        return name;
    }

    @Column(name = "Prize")
    @NotNull
    public double getPrize() {
        return prize;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPrize(double prize) {
        this.prize = prize;
    }
}
