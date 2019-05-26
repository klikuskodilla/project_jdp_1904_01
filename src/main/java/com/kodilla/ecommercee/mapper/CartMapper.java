package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.UserAccauntDto;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public Cart mapToCart(final UserAccauntDto userAccauntDto) throws ParseException {
        return new Cart(
                userMapper.mapToUserWithAllParam(userService.getUser(userAccauntDto))
        );
    }

    public CartDto mapToCartdto(final Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getProductList().stream()
                .map(c -> new ProductDto(
                        c.getId(),
                        c.getName(),
                        c.getPrize()))
                .collect(Collectors.toList()),
                cart.getUser().getId());
    }
}
