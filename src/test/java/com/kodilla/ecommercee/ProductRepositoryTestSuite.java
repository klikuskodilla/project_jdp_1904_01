package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    private static final String NAME = "new product";

    @Test
    public void testProductRepositorySave() {
        //Given
        Product product1 = new Product(NAME, 2.22);

        //When
        productRepository.save(product1);

        //Then
        long id = product1.getId();
        String n = product1.getName();
        Optional<Product> prod = productRepository.findById(id);
        Assert.assertTrue(prod.isPresent());
        Assert.assertEquals("new product", n);

        //Clean Up
        productRepository.deleteById(id);
    }
}
