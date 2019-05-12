package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroup {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(targetEntity =  Product.class,
            mappedBy = "productGroup",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Product> products = new ArrayList<>();

}


