package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderMapper {
    public List<OrderDto> mapToOrderDtoList(List<Order> orders) {
        return null;
    }

    public Order mapToOrder(OrderDto orderDto) {
        return null;
    }

    public OrderDto mapToOrderDto(Order order) {
        return null;
    }
}
