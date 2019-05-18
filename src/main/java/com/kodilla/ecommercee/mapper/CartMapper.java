package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public Cart maptoCart(CartDto cartDto) {
        return new Cart();
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto();
    }

    public List<CartDto> mapToCartDtoList(List<Cart> cartList) {
        return cartList.stream()
                .map(cart -> new CartDto())
                .collect(Collectors.toList());
    }
}
