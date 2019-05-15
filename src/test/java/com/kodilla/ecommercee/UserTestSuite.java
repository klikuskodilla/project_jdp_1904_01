package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dao.*;
import com.kodilla.ecommercee.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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
        Order order = new Order("Open");
        Cart cart = new Cart();
        User user = new User("Name1", "password1");

        cartDao.save(cart);
        orderDao.save(order);
        userDao.save(user);

        cart.setUser(user);
        order.setUser(user);
        user.getCartList().add(cart);
       // order.setCart(cart);
        user.getOrderList().add(order);

        System.out.println(user.getOrderList().get(0).getStatus());

        cartDao.save(cart);
        orderDao.save(order);
        userDao.save(user);

        User userTest = userDao.findById(user.getId()).get();
        System.out.println(userTest.getId());
        System.out.println(userTest.getUserName());
        System.out.println(userTest.getPassword());
        System.out.println(userTest.getTimeGenerateKey());
        System.out.println(userTest.getUserKey());
        System.out.println(userTest.getCartList().get(0).getId());
        System.out.println(userTest.getOrderList().size());
        System.out.println(orderDao.findById(1L).get().getStatus());

//        Long userId = user.getId();
//        int userKey = user.getUserKey();
//        Date timeKeyGenerate = user.getTimeGenerateKey();
//        boolean status = user.isStatus();
//        Long cartId = user.getCartList().get(0).getId();
////        System.out.println(userDao);
//     //   Long orderId = user.getOrderList().get(0).getId();
//
//        User userTest = userDao.findById(userId).get();
//        //Then
//        assertEquals("Name1", userTest.getUserName());
//        assertEquals("password1", userTest.getPassword());
//        assertEquals(userKey, userTest.getUserKey());
//        assertEquals(timeKeyGenerate, userTest.getTimeGenerateKey());
//        assertEquals(status, userTest.isStatus());
//        //System.out.println("asaasas " + userTest.getCartList().size());
//        //assertEquals(cartId, userTest.getCartList().get(0).getId())1;
//        //assertEquals(orderId, userTest.getOrderList().get(0).getId());
//
//        //Clean Up
//      //  orderDao.deleteById(orderId);
//        cartDao.deleteById(cartId);
//        userDao.deleteById(userId);

    }
}
