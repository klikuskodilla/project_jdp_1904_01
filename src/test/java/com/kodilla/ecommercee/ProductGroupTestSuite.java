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
    ProductGroupDao groupDao;

    @Test
    public void testSaveProductGroup() {
        //Given
        ProductGroup group = new ProductGroup("group description");

        //When
        groupDao.save(group);

        //Then
        Long id = group.getId();
        Optional<ProductGroup> productGroup = groupDao.findById(id);
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
        Product prod1 = productGroup.getProducts().get(0);
        String prod1Name = prod1.getName();
        String productGroupDesc = product1.getProductGroup().getDescription();
        String groupDescription = productGroup.getDescription();

        //Then
        assertFalse(productGroup.getProducts().isEmpty());
        assertEquals(3, productGroup.getProducts().size());
        assertEquals(groupDescription, productGroupDesc);
        assertEquals("product1", prod1Name);

        //Clean
        groupDao.deleteById(id);
    }
}
