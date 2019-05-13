package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTestSuite {
    @Autowired
    private ProductDao productDao;

    @Test
    public void createTest(){
        //Given
        Product product = new Product("product", 12.5);
        //When
        productDao.save(product);
        Long id = product.getId();

        //Then
        assertEquals( "product", productDao.findById(id).get().getName());
        assertEquals( 12.5, productDao.findById(id).get().getPrize(),0.001);

        //Clean Up
        productDao.deleteById(id);
    }

    @Test
    public void findAllTest(){
        //Given
        Product product = new Product("product", 11.5);
        Product product2 = new Product("product2", 12.5);
        Product product3 = new Product("product3", 13.5);

        //When
        productDao.save(product);
        productDao.save(product2);
        productDao.save(product3);

        Iterator<Product> productIterator = productDao.findAll().iterator();
        ArrayList<Product> products = new ArrayList<>();

        while (productIterator.hasNext()){
            products.add(productIterator.next());
        }

        //Then
        assertEquals(3, products.size());

        //Clean Up
        productDao.deleteById(product3.getId());
        productDao.deleteById(product2.getId());
        productDao.deleteById(product.getId());
    }

    @Test
    public void findById(){
        //Given
        Product product = new Product("product", 11.5);
        Product product2 = new Product("product2", 12.5);
        Product product3 = new Product("product3", 13.5);

        //When
        productDao.save(product);
        productDao.save(product2);
        productDao.save(product3);

        //Then
        assertEquals("product2", productDao.findById(product2.getId()).get().getName());
        assertEquals(12.5, productDao.findById(product2.getId()).get().getPrize(),0.01);

        //Clean Up
        productDao.deleteById(product3.getId());
        productDao.deleteById(product2.getId());
        productDao.deleteById(product.getId());
    }

    @Test
    public void deleteById(){
        //Given
        Product product = new Product("product", 11.5);
        Product product2 = new Product("product2", 12.5);
        Product product3 = new Product("product3", 13.5);

        //When
        productDao.save(product);
        productDao.save(product2);
        productDao.save(product3);

        Iterator<Product> productIterator = productDao.findAll().iterator();
        ArrayList<Product> products = new ArrayList<>();

        while (productIterator.hasNext()){
            products.add(productIterator.next());
        }

        //Then
        assertEquals(3, products.size());

        productDao.deleteById(product3.getId());
        productDao.deleteById(product2.getId());
        productDao.deleteById(product.getId());

        products.clear();
        productIterator = productDao.findAll().iterator();
        while (productIterator.hasNext()){
            products.add(productIterator.next());
        }

        assertEquals(0, products.size());
    }
}
