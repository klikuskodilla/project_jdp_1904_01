package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDao orderDao;

    public List<OrderDto> getAllOrders() {
        return orderMapper.mapToOrderDtoList(orderDao.findAll());
    }

    public OrderDto getOrder(Long id) {
        return orderMapper.mapToOrderDto(orderDao.findById(id).orElse(new Order()));
    }

    public Order save(OrderDto orderDto) {
        return orderDao.save(orderMapper.mapToOrder(orderDto));
    }

    public void deleteOrderById(Long id) {
        orderDao.deleteById(id);
    }
}
