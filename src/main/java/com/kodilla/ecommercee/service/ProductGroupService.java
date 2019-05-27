package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.ProductGroupDao;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupService {
    @Autowired
    ProductGroupDao productGroupDao;

    public ProductGroup getGroup(Long productGroupId) throws GroupNotFoundException {
        return productGroupDao.findById(productGroupId).orElseThrow(GroupNotFoundException::new);
    }

    public ProductGroup saveProductGroup(final ProductGroup productGroup) {
        return productGroupDao.save(productGroup);
    }

    public void deleteProductGroup(final Long id) {
        productGroupDao.deleteById(id);
    }

    public List<ProductGroup> getAllGroups() {
        return productGroupDao.findAll();
    }
}