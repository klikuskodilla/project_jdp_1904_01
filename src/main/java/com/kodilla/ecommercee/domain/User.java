package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.Cart;
import lombok.Data;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;
import java.util.stream.IntStream;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "STATUS")
    private boolean status;

    @Column(name = "USER_KEY")
    private int userKey;

    @Column(name = "TIME_GENERATE_KEY")
    private Date timeGenerateKey;

    @Column(name = "PASSWORD")
    private String password;

//    @OneToMany(
//            targetEntity = Cart.class,
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<Cart> cartList = new ArrayList<>();

//    @OneToMany(
//            targetEntity = Order.class,
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private Order order;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        status = true;
        userKey = keyGenerator();
        timeGenerateKey = new Date();
    }

    public int keyGenerator(){
        Random random = new Random();
        return random.nextInt(89999)+10001;
    }
}
