package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTestSuite {
    @Autowired
    private ProductDao productDao;

    @Test
    public void saveAndFindByIdTest(){
        //Given
        Product product = new Product("product", 12.5);
        //When
        productDao.save(product);
        Long id = product.getId();
        Product productTest = productDao.findById(id).get();

        //Then
        assertEquals( "product", productTest.getName());
        assertEquals( 12.5, productTest.getPrize(),0.001);

        //Clean Up
        productDao.deleteById(id);
    }
}
