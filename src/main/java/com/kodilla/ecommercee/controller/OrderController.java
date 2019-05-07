package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addOrder")
    public void addOrder(OrderDto orderDto) {
        System.out.println("new order added");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(Long orderId) {
        return new OrderDto(1l, "new");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    private OrderDto updateOrder(OrderDto orderDto) {
        return new OrderDto(1l,"updated");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(Long orderId) {
        System.out.println("product: " + orderId + " deleted");
    }
}
