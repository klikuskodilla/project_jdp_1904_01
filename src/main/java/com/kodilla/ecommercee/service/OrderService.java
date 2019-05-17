package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public List<Order> getOrders() {
        return orderDao.findAll();
    }

    public Optional<Order> getOrder(Long id) {
        return orderDao.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }

    public void deleteOrder(Long id) {
        orderDao.deleteById(id);
    }
}
