package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private Date dateCreated;
    private String status;
    private String nameUser;
    private List<Product> orderedProducts = new ArrayList<>();

    public OrderDto(Long id, String status) {
        this.id = id;
        this.dateCreated = new Date();
        this.status = status;
    }
}
