package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getCart")
    public CartDto getCart(@RequestParam Long cartId) throws CartNotFoundException {
        return cartService.getCart(cartId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductFromCart")
    public List<Product> getProductFromCart(@RequestParam Long cartId) throws CartNotFoundException {
        return cartService.getProducts(cartId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public void createCart(@RequestBody UserAccauntDto userAccauntDto) throws ParseException {
    cartService.saveCart(cartMapper.mapToCart(userAccauntDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart")
    public String addProductToCart(@RequestParam Long productId, @RequestBody UserAccauntDto userAccauntDto){
        return "Add product to cart";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(@RequestBody DeleteProductFromCartDto deleteProductFromCartDto){
        System.out.println("Product with ID number removed");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(Long cartId){
        System.out.println("An order was created for the cart with the Id number");
    }
}
