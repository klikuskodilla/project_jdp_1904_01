package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderDao extends CrudRepository<Order, Long> {

    @Override
    List<Order> findAll();
}
