package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.dao.ProductGroupDao;
import com.kodilla.ecommercee.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTestSuite {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductGroupDao productGroupDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;

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

    @Test
    public void relationshipTests(){
        //Given
        Product product = new Product("Shoes", 100);
        ProductGroup productGroup = new ProductGroup("Sport shoes");
        Cart cart = new Cart();
        Order order = new Order("OPEN");

        product.setOrder(order);
        product.setCart(cart);
        product.setProductGroup(productGroup);

        productGroup.getProducts().add(product);
        cart.getProductList().add(product);
        order.getOrderedProducts().add(product);
        order.setCart(cart);

        //When
        productDao.save(product);
        cartDao.save(cart);
        orderDao.save(order);
        productGroupDao.save(productGroup);

        Product productTest = productDao.findById(product.getId()).get();
        ProductGroup productGroupTest = productGroupDao.findById(productGroup.getId()).get();
        Cart cartTest = cartDao.findById(cart.getId()).get();
        Order orderTest = orderDao.findById(order.getId()).get();

        //Then
        assertEquals("Shoes", productTest.getName());
        assertEquals(100, productTest.getPrize(), 0.01);
        assertEquals("Sport shoes", productTest.getProductGroup().getDescription());
        assertEquals(cart.getId(), productTest.getCart().getId());
        assertEquals("OPEN", productTest.getOrder().getStatus());

        assertEquals("Shoes", productGroupTest.getProducts().get(0).getName());
        assertEquals(100, productGroupTest.getProducts().get(0).getPrize(), 0.01);

        assertEquals("Shoes", cartTest.getProductList().get(0).getName());
        assertEquals(100, cartTest.getProductList().get(0).getPrize(), 0.01);

        assertEquals("Shoes", orderTest.getOrderedProducts().get(0).getName());
        assertEquals(100, orderTest.getOrderedProducts().get(0).getPrize(), 0.01);
        //Clean Up
        orderDao.deleteById(order.getId());
        cartDao.deleteById(cart.getId());
        productGroupDao.deleteById(productGroup.getId());
        productDao.deleteById(product.getId());
    }
}
