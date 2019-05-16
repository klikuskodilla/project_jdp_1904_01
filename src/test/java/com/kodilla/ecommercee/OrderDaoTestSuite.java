package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTestSuite {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    ProductDao productDao;

    @Transactional
    @Test
    public void testOrderDaoSave() {

        //Given
        Cart cart = new Cart();
        Order order = new Order("open");
        User user = new User("Anna", "password");
        order.setUser(user);
        order.setCart(cart);

        //When
        userDao.save(user);
        Long userId = user.getId();
        orderDao.save(order);
        Long orderId = order.getId();
        cartDao.save(cart);

        Optional<Order> testOrder = orderDao.findById(orderId);

        //Then
        assertEquals("open", testOrder.get().getStatus());
        assertEquals("Anna", testOrder.get().getUser().getUserName());

        //CleanUp
        userDao.deleteById(userId);
    }
    @Transactional
    @Test
    public void testOrderDaoFindById() {

        //Given
        Cart cart = new Cart();
        Cart cart2 = new Cart();

        Order order = new Order("open");
        Order order2 = new Order("closed");

        User user = new User("Anna", "password_Anna");
        User user2 = new User("Mark", "password_Mark");

        order.setUser(user);
        order.setUser(user2);
        order.setCart(cart);
        order.setCart(cart2);

        userDao.save(user);
        Long userId = user.getId();
        userDao.save(user2);
        Long user2Id = user2.getId();
        orderDao.save(order);
        Long orderId = order.getId();
        orderDao.save(order2);
        Long order2Id = order2.getId();

        //When
        Optional<Order> testOrder = orderDao.findById(orderId);
        Optional<Order> testOrder2 = orderDao.findById(order2Id);

        //Then
        assertEquals(true, testOrder.isPresent());
        assertEquals("open", testOrder.get().getStatus());
        assertEquals("closed", testOrder2.get().getStatus());

        //CleanUp
        userDao.deleteById(userId);
        userDao.deleteById(user2Id);
    }
    @Transactional
    @Test
    public void testOrderDaoFindAll() {

        //Given
        Cart cart = new Cart();
        Cart cart2 = new Cart();

        Order order = new Order("open");
        Order order2 = new Order("closed");

        User user = new User("Anna", "password_Anna");
        User user2 = new User("Mark", "password_Mark");

        order.setUser(user);
        order.setUser(user2);
        order.setCart(cart);
        order.setCart(cart2);

        userDao.save(user);
        Long userId = user.getId();
        userDao.save(user2);
        Long user2Id = user2.getId();
        orderDao.save(order);
        Long orderId =order.getId();
        orderDao.save(order2);
        Long order2Id =order.getId();
        cartDao.save(cart);

        //When
        ArrayList<Order> orders = new ArrayList<>();
        Iterator<Order> orderIterator = orderDao.findAll().iterator();
        orderIterator.forEachRemaining(orders::add);

        //Then
        assertEquals(2, orders.size());

        //CleanUp
        orderDao.deleteAll();
        userDao.deleteById(userId);
        userDao.deleteById(user2Id);
    }

    @Transactional
    @Test
    public void testOrderRelations() {

        //Given
        Cart cart = new Cart();
        Order order = new Order("open");
        User user = new User("Anna", "password");
        Product product = new Product("product", 10.0);

        //When
        order.setCart(cart);
        order.setUser(user);
        order.getOrderedProducts().add(product);
        user.getOrderList().add(order);
        cart.getProductList().add(product);
        cart.setUser(user);
        product.setOrder(order);
        product.setCart(cart);

        userDao.save(user);
        orderDao.save(order);
        cartDao.save(cart);
        productDao.save(product);

        Order testOrder = orderDao.findById(order.getId()).get();

        //Then
        assertEquals("open", testOrder.getStatus());
        assertEquals("Anna", testOrder.getUser().getUserName());
        assertEquals("password", testOrder.getUser().getPassword());
        assertEquals("product", testOrder.getOrderedProducts().get(0).getName());
        assertEquals(10.0, testOrder.getOrderedProducts().get(0).getPrize(), 0.01);

        //Clean Up
        productDao.deleteById(product.getId());
        userDao.deleteById(user.getId());

        }
    }
