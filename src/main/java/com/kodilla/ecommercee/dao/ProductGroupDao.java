package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductGroupDao extends CrudRepository<ProductGroup, Long> {

    @Override
    List<ProductGroup> findAll();
}
