package com.kodilla.ecommercee.domain;

public class ProductDto {
    private Long id;
    private String name;
    private double prize;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, double prize) {
        this.id = id;
        this.name = name;
        this.prize = prize;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrize() {
        return prize;
    }
}
