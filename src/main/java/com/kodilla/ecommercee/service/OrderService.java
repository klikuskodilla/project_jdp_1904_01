package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderMapper orderMapper;

    public Order saveOrder(final Order order) {
        return orderDao.save(order);
    }

    public Optional<Order> getOrder(final Long orderId) {
        return orderDao.findById(orderId);
    }

    public void deleteOrder(final Long id) {
        orderDao.deleteById(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }
}