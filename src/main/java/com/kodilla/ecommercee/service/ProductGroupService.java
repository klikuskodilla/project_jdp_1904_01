package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.ProductGroupDao;
import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductGroupService {
    @Autowired
    private ProductGroupDao groupDao;

    public List<ProductGroup> getGroups() {
        return groupDao.findAll();
    }

    public Optional<ProductGroup> getGroup(Long id) {
        return groupDao.findById(id);
    }

    public void saveGroup(ProductGroup productGroup) {
        groupDao.save(productGroup);
    }
}
