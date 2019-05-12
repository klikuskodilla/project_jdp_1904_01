package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrderDao extends CrudRepository<Order,Long> {

    @Override
    Order save(Order order);

    @Override
    void deleteById(Long Id);

    @Override
    List<Order> findAll();

    @Override
    Optional<Order> findById(Long Id);
}
