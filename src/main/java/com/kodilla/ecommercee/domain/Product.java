package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT_T")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "PRIZE")
    private double prize;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "PRODUCTGROUP_ID")
    private ProductGroup productGroup;

    @ManyToOne
    @JoinColumn(name = "USERORDER_ID")
    private Order order;

    public Product(String name, double prize) {
        this.name = name;
        this.prize = prize;
    }

    public Product(String name, double prize, ProductGroup productGroup) {
        this.name = name;
        this.prize = prize;
        this.productGroup = productGroup;
    }

    public Product(Long id, String name, double prize, ProductGroup productGroup) {
        this.id = id;
        this.name = name;
        this.prize = prize;
        this.productGroup = productGroup;
    }
}