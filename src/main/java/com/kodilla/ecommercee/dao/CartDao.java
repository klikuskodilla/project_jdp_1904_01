package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartDao extends CrudRepository<Cart, Long> {
    List<Cart> findAll();
    Cart save(Cart cart);
    Optional<Cart> findById(Long id);
}

