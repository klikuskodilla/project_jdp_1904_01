package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Table(name = "Products")

public class Product {
    private Long id;
    private String name;
    private double prize;

    public Product(String name, double prize) {
        this.name = name;
        this.prize = prize;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    public Long getId() {
        return id;
    }

    @NotNull
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    @NotNull
    @Column(name = "Prize")
    private double getPrize() {
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
