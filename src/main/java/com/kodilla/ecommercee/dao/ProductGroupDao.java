package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGroupDao extends CrudRepository<ProductGroup, Long> {
}
