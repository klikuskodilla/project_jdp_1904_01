package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public Cart maptoCart(CartDto cartDto) {
        return new Cart(
                cartDto.getId()
        );
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getId()
        );
    }

    public List<CartDto> mapToCartDtoList(List<Cart> cartList) {
        return cartList.stream()
                .map(cart -> new CartDto(cart.getId()))
                .collect(Collectors.toList());
    }
}
