package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDao productDao;

    public List<ProductDto> getAllProducts() {
        return productMapper.mapToProductDtoList(productDao.findAll());
    }

    public ProductDto getProduct(Long id) throws ProductNotFoundException {
        return productMapper.mapToProductDto(productDao.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    public void deleteProductById(Long id) {
        productDao.deleteById(id);
    }
}
