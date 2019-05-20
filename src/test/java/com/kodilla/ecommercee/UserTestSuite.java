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
import javax.transaction.Transactional;
import java.util.Date;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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

        //Clean Up
        orderDao.deleteById(order.getId());
        cartDao.deleteById(cart.getId());
        userDao.deleteById(userId);
    }
}
