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
@Transactional
public class OrderDaoTestSuite {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    ProductDao productDao;

    @Test
    public void testOrderDaoSave() {

        //Given
        Cart cart = new Cart();
        Order order = new Order("open");
        User user = new User("Anna", "password");
        order.setUser(user);
        order.setCart(cart);
        cart.setUser(user);

        //When
        userDao.save(user);
        Long userId = user.getId();
        orderDao.save(order);
        Long orderId = order.getId();
        cartDao.save(cart);
        Long cartId = cart.getId();

        Optional<Order> testOrder = orderDao.findById(orderId);
        String status = testOrder.get().getStatus();
        String username = testOrder.get().getUser().getUserName();
        String userCart = testOrder.get().getCart().getUser().getUserName();

        //Then
        assertEquals("open", status);
        assertEquals("Anna", username);
        assertEquals("Anna",userCart);

        //CleanUp
        orderDao.deleteById(orderId);
        userDao.deleteById(userId);
    }

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
        order2.setUser(user2);
        order.setCart(cart);
        order2.setCart(cart2);
        cart.setUser(user);
        cart2.setUser(user2);

        userDao.save(user);
        Long userId = user.getId();
        userDao.save(user2);
        Long user2Id = user2.getId();
        orderDao.save(order);
        Long orderId = order.getId();
        orderDao.save(order2);
        Long order2Id = order2.getId();
        cartDao.save(cart);
        Long cartId = cart.getId();
        cartDao.save(cart);
        Long cart2Id = cart2.getId();

        //When
        Optional<Order> testOrder = orderDao.findById(orderId);
        Optional<Order> testOrder2 = orderDao.findById(order2Id);
        String status = testOrder.get().getStatus();
        String status2 = testOrder2.get().getStatus();
        String username2 = testOrder2.get().getUser().getUserName();

        //Then
        assertEquals(true, testOrder.isPresent());
        assertEquals("open", status);
        assertEquals("closed", status2);
        assertEquals("Mark", username2);

        //CleanUp
        orderDao.deleteById(orderId);
        orderDao.deleteById(order2Id);
        userDao.deleteById(userId);
        userDao.deleteById(user2Id);
    }

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
        order.setCart(cart);
        order2.setUser(user2);
        order2.setCart(cart2);

        userDao.save(user);
        Long userId = user.getId();
        userDao.save(user2);
        Long user2Id = user2.getId();
        orderDao.save(order);
        Long orderId = order.getId();
        orderDao.save(order2);
        Long order2Id = order2.getId();
        cartDao.save(cart);
        cartDao.save(cart2);

        //When
        ArrayList<Order> orders = new ArrayList<>();
        Iterator<Order> orderIterator = orderDao.findAll().iterator();
        orderIterator.forEachRemaining(orders::add);

        //Then
        assertEquals(2, orders.size());

        //CleanUp
        orderDao.deleteById(orderId);
        orderDao.deleteById(order2Id);
        userDao.deleteById(userId);
        userDao.deleteById(user2Id);
    }

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
    //    cartDao.save(cart);
        productDao.save(product);

        Order testOrder = orderDao.findById(order.getId()).get();
        String status = testOrder.getStatus();
        String username = testOrder.getUser().getUserName();
        String password = testOrder.getUser().getPassword();
        String productName = testOrder.getOrderedProducts().get(0).getName();
        Double productPrice = testOrder.getOrderedProducts().get(0).getPrize();

        //Then
        assertEquals("open", status);
        assertEquals("Anna", username);
        assertEquals("password", password);
        assertEquals("product", productName);
        assertEquals(10.0, productPrice, 0.01);

        //Clean Up
        productDao.deleteById(product.getId());
        userDao.deleteById(user.getId());
    }
}
