package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private Date dateCreated;
    private String status;

    public OrderDto(Long id, String status) {
        this.id = id;
        this.dateCreated = new Date();
        this.status = status;
    }
}
