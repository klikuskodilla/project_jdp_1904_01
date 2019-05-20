package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.ProductGroupDao;
import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductGroupService {

    @Autowired
    ProductGroupDao productGroupDao;

    public Optional<ProductGroup> getGroup(Long productGroupId){
        return productGroupDao.findById(productGroupId);
    }

    public ProductGroup saveProductGroup(final ProductGroup productGroup){
        return productGroupDao.save(productGroup);
    }
    public void deleteProductGroup(final Long id){
         productGroupDao.deleteById(id);
    }

    public List<ProductGroup> getAllGroups() {
        ArrayList<ProductGroup> groups = new ArrayList<>();
        Iterator<ProductGroup> groupIterator = productGroupDao.findAll().iterator();
        while (groupIterator.hasNext()){
            groups.add(groupIterator.next());
        }
        return groups;
    }
}
