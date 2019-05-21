package com.kodilla.ecommercee.service;

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

    public ProductDto getProduct(Long id) {
        return productMapper.mapToProductDto(productDao.findById(id).orElse(new Product()));
    }

    public Product save(ProductDto productDto) {
        return productDao.save(productMapper.mapToProduct(productDto));
    }

    public void deleteOrderById(Long id) {
        productDao.deleteById(id);
    }
}
