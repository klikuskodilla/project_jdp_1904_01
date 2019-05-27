package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CreateProductDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.UpdatedProductDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(@RequestParam Long productId) throws ProductNotFoundException {
        return productService.getProduct(productId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct")
    public void createProduct(@RequestBody CreateProductDto createProductDto) throws GroupNotFoundException {
        productService.saveProduct(productMapper.mapToProduct(createProductDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    private void updateProduct(@RequestBody UpdatedProductDto updatedProductDto) throws GroupNotFoundException{
        productService.saveProduct(productMapper.mapToProduct(updatedProductDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {
        productService.deleteProductById(productId);
    }
}
