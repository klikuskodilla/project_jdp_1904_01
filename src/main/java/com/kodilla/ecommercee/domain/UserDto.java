package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private long id;
    private String userName;
    private boolean status;
    private int userKey;
    private String timeGenerateKey;
    private String password;
    private List<CartDto> cartDtoList = new ArrayList<>();
    private List<OrderDto> orderDtoList = new ArrayList<>();
}
