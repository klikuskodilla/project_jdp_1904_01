package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "Id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "Date_Created")
    private Date dateCreated;

    @NotNull
    @Column(name = "Status")
    private String status;

//    @OneToMany(targetEntity = Product.class,
//            mappedBy = order,
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY)
//    private List<Product> orderedProducts = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "User_ID")
//    private User user;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "Cart_ID")
//    private Cart cart;

    public Order(Long id, String status) {
        this.id = id;
        dateCreated = new Date();
        this.status = status;
    }
}