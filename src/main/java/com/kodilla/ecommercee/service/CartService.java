package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.DeleteProductFromCartDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private UserService userService;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartMapper cartMapper;

    public Cart saveCart(final Cart cart){
        return cartDao.save(cart);
    }

    public CartDto getCart(final Long cartId) throws CartNotFoundException {
        return cartMapper.mapToCartdto(cartDao.findById(cartId).orElseThrow(CartNotFoundException::new));
    }

    public List<Product> getProducts(final Long cartId) throws CartNotFoundException {
        return cartDao.findById(cartId).orElseThrow(CartNotFoundException::new).getProductList();
    }

    public String deleteProductFromCart(final DeleteProductFromCartDto deleteProductFromCartDto) throws CartNotFoundException {
        Optional<Product> productName = cartDao.findById(deleteProductFromCartDto.getCartId()).orElseThrow(CartNotFoundException::new).getProductList().stream()
                .filter(p -> p.getId() == deleteProductFromCartDto.getProductId())
                .findFirst();

        cartDao.findById(deleteProductFromCartDto.getCartId())
                .orElseThrow(CartNotFoundException::new).getProductList()
                .remove(deleteProductFromCartDto.getProductId());
        return "Delete product " + productName + "from cart with number " + deleteProductFromCartDto.getCartId();
    }
}
