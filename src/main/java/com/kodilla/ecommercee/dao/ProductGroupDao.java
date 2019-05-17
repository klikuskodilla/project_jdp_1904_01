package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductGroupDao extends CrudRepository<ProductGroup, Long> {
    List<ProductGroup> findAll();
    Optional<ProductGroup> findById(Long id);
    ProductGroup save(ProductGroup productGroup);
}
