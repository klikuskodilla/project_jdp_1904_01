package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductMapper {
    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return null;
    }

    public Product mapToProduct(ProductDto productDto) {
        return null;
    }

    public ProductDto mapToProductDto(Product product) {
        return null;
    }
}
