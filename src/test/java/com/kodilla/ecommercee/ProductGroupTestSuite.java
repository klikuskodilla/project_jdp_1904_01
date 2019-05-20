package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dao.ProductGroupDao;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductGroupTestSuite {
    @Autowired
    private ProductGroupDao groupDao;

    @Test
    public void testSaveProductGroup() {
        //Given
        ProductGroup group = new ProductGroup("group description");

        //When
        groupDao.save(group);

        //Then
        Long id = group.getId();
        Optional<ProductGroup> productGroup = groupDao.findById(id);
        ProductGroup testGroup = productGroup.get();
        String testGroupDescription = testGroup.getDescription();

        assertEquals("group description", testGroupDescription);
        assertTrue(productGroup.isPresent());

        //Clean
        groupDao.deleteById(id);
    }

    @Test
    public void testProductSaveToProductGroup() {
        //Given
        Product product1 = new Product("product1", 2.2);
        Product product2 = new Product("product2", 2.2);
        Product product3 = new Product("product3", 2.2);
        ProductGroup productGroup = new ProductGroup("products description");

        product1.setProductGroup(productGroup);
        product2.setProductGroup(productGroup);
        product3.setProductGroup(productGroup);
        productGroup.getProducts().add(product1);
        productGroup.getProducts().add(product2);
        productGroup.getProducts().add(product3);

        //When
        groupDao.save(productGroup);
        Long id = productGroup.getId();
        ProductGroup testGroup = groupDao.findById(id).get();
        Product testProduct = testGroup.getProducts().get(0);
        String testProductName = testProduct.getName();
        String testProductDescription = testProduct.getProductGroup().getDescription();


        //Then
        assertFalse(testGroup.getProducts().isEmpty());
        assertEquals(3, testGroup.getProducts().size());
        assertEquals("products description", testProductDescription);
        assertEquals("product1", testProductName);

        //Clean
        groupDao.deleteById(id);
    }
}
