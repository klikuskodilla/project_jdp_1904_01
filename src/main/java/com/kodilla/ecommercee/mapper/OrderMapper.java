package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getStatus()
        );
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getDateCreated(),
                order.getStatus()
        );
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(order -> new OrderDto(order.getId(), order.getDateCreated(), order.getStatus()))
                .collect(Collectors.toList());
    }
}
