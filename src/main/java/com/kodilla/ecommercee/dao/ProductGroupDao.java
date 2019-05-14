package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductGroupDao extends CrudRepository<ProductGroup, Long> {
}
