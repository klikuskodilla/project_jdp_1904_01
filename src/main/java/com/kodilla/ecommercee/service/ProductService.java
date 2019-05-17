package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> getProducts() {
        return productDao.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productDao.findById(id);
    }

    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }
}
