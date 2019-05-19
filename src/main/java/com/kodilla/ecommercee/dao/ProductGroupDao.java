package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductGroupDao extends CrudRepository<ProductGroup, Long> {
    List<ProductGroup> findAll();
}
