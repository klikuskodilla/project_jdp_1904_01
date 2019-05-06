package com.kodilla.ecommercee.domain.cart;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cart {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}