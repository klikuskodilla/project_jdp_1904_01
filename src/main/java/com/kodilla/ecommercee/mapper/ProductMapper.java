package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ProductGroupService;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductGroupService productGroupService;
    @Autowired
    private CartService cartService;

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getPrize()))
                .collect(Collectors.toList());
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrize()
        );
    }

    public Product mapToProduct(final CreateProductDto createProductDto) throws GroupNotFoundException {
        return new Product(
                createProductDto.getProductName(),
                createProductDto.getPrize(),
                productGroupService.getGroup(createProductDto.getProductGroupId()));
    }

    public Product mapToProduct(final UpdatedProductDto updatedProductDto) throws GroupNotFoundException {
        Product product = new Product(
                updatedProductDto.getProductName(),
                updatedProductDto.getPrize(),
                productGroupService.getGroup(updatedProductDto.getProductGroupId()));
        product.setId(updatedProductDto.getId());
        return product;
    }
}
