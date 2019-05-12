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
@Table(name = "Product_T")
public class Product {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
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
    private ProductGroup productGroup;

    @ManyToOne
    @JoinColumn(name = "UserOrder_ID")
    private Order order;

    public Product(String name, double prize) {
        this.name = name;
        this.prize = prize;
    }
}
