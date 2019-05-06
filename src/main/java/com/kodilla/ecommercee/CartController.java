package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.cart.CartDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<CartDto> getAllProductsFromCart(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public void createCart(CartDto cartDto){
        System.out.println("New Cart Created");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart")
    public void addProductToCart(Long productId){
        System.out.println("Add product to cart");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(Long productId){
        System.out.println("Product with ID number removed");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(Long cartId){
        System.out.println("An order was created for the cart with the Id number");
    }
}
