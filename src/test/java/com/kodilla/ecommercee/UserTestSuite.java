package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestSuite {
    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;

    @Test
    public void saveAndFindByIdTest(){
        //Given
        Order order = new Order("OPEN");
        Cart cart = new Cart();
        User user = new User("Name1", "password1");

        cartDao.save(cart);
        userDao.save(user);
        orderDao.save(order);

        //When
        cart.setUser(user);
        user.getCartList().add(cart);

        order.setUser(user);
        user.getOrderList().add(order);

        order.setCart(cart);

        cartDao.save(cart);
        userDao.save(user);
        orderDao.save(order);

        Long userId = user.getId();
        int userKey = user.getUserKey();
        Date timeKeyGenerate = user.getTimeGenerateKey();
        boolean status = user.isStatus();
        int cartSize = user.getCartList().size();
        int orderSize = user.getOrderList().size();
        String orderStatus = user.getOrderList().get(0).getStatus();

        User userTest = userDao.findById(userId).get();

        //Then
        assertEquals("Name1", userTest.getUserName());
        assertEquals("password1", userTest.getPassword());
        assertEquals(userKey, userTest.getUserKey());
        assertEquals(timeKeyGenerate, userTest.getTimeGenerateKey());
        assertEquals(status, userTest.isStatus());
        assertEquals(cartSize, userTest.getCartList().size());
        assertEquals(orderSize, userTest.getOrderList().size());
        assertEquals(orderStatus, userTest.getOrderList().get(0).getStatus());

        System.out.println();
        System.out.println("user name: " + userTest.getUserName());
        System.out.println("password: " + userTest.getPassword());
        System.out.println("key: " + userTest.getUserKey());
        System.out.println("key time genrate: " + userTest.getTimeGenerateKey());
        System.out.println("user status: " + userTest.isStatus());
        System.out.println("size cart list : " + userTest.getCartList().size());
        System.out.println("size order list: " + userTest.getOrderList().size());
        System.out.println("order status: " + userTest.getOrderList().get(0).getStatus());
        System.out.println();

        //Clean Up
        orderDao.deleteById(order.getId());
        cartDao.deleteById(cart.getId());
        userDao.deleteById(userId);
    }
}
