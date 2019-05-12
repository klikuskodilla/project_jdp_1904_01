package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long Id);

    @Override
    Product save (User user);

    @Override
    void deleteById(Long Id);
}