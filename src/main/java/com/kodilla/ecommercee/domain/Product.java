package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Product")

public class Product {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "Name")
    private String name;

    @NotNull
    @Column(name = "Prize")
    private double prize;

    @ManyToOne
    @JoinColumn(name = "Cart_ID")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ProductGroup_ID")
    private ProductGroup product;

//    @ManyToOne
//    @JoinColumn(name = "Order_ID")
//    private Order order;

    public Product(String name, double prize) {
        this.name = name;
        this.prize = prize;
    }
}
