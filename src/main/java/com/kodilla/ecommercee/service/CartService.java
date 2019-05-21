package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartMapper cartMapper;

    public List<CartDto> getAllCarts() {
        return cartMapper.mapToCartDtoList(cartDao.findAll());
    }

    public CartDto getCart(Long id) {
        return cartMapper.mapToCartDto(cartDao.findById(id).orElse(new Cart()));
    }

    public Cart save(CartDto cartDto) {
        return cartDao.save(cartMapper.mapToCart(cartDto));
    }
}
