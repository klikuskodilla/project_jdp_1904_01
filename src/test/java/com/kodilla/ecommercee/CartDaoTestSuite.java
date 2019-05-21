package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartDaoTestSuite {

    @Autowired
    UserDao userDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    ProductDao productDao;

    @Test
    public void saveTest() {

        //Given
        User user = new User("Anna","password");
        Product product = new Product("product",100);
        Cart cart = new Cart();

        productDao.save(product);
        userDao.save(user);
        cartDao.save(cart);

        cart.setUser(user);
        product.setCart(cart);
        cart.getProductList().add(product);

        productDao.save(product);
        Long productId = product.getId();
        userDao.save(user);
        Long userId = user.getId();
        cartDao.save(cart);
        Long cartId = cart.getId();

        //When
        Optional<Cart> cartTest = cartDao.findById(cartId);
        String username = cartTest.get().getUser().getUserName();
        String password = cartTest.get().getUser().getPassword();
        double productPrice = cartTest.get().getProductList().get(0).getPrize();
        String productName = cartTest.get().getProductList().get(0).getName();

        //Then
        assertEquals("password",password);
        assertEquals("Anna",username);
        assertEquals(100,productPrice,001);
        assertEquals("product",productName);

        //CleanUp
        cartDao.deleteById(cartId);
        productDao.deleteById(productId);
        userDao.deleteById(userId);
    }

    @Test
    public void findByIdTest() {

        //Given
        User user = new User("Anna","password");
        Product product = new Product("product",100);
        Cart cart = new Cart();

        productDao.save(product);
        userDao.save(user);
        cartDao.save(cart);

        cart.setUser(user);
        product.setCart(cart);
        List<Product> productList = new ArrayList<>();
        cart.getProductList().add(product);
        cart.setProductList(productList);

        productDao.save(product);
        Long productId = product.getId();
        userDao.save(user);
        Long userId = user.getId();
        cartDao.save(cart);
        Long cartId = cart.getId();

        //When
        Optional<Cart> cartTest = cartDao.findById(cartId);
        String username = cartTest.get().getUser().getUserName();
        double productListSize = cartTest.get().getProductList().size();

        //Then
        assertEquals(true,cartTest.isPresent());
        assertEquals("Anna",username);
        assertEquals(1,productListSize,001);

        //CleanUp
         cartDao.deleteById(cartId);
         productDao.deleteById(productId);
         userDao.deleteById(userId);
    }

    @Test
    public void findAllTest() {

        //Given
        User user = new User("Anna","password");
        userDao.save(user);
        Long userId = user.getId();
        Cart cart = new Cart();
        cartDao.save(cart);
        Long cartId = cart.getId();

        Product product = new Product("product",100);
        productDao.save(product);
        Long productId = product.getId();
        cart.setUser(user);
        List<Product> productList = new ArrayList<>();
        cart.getProductList().add(product);
        cart.setProductList(productList);

        //When
        ArrayList<Cart> carts = new ArrayList<>();
        Iterator<Cart> cartList = cartDao.findAll().iterator();
        cartList.forEachRemaining(carts::add);

        //Then
        assertEquals(1,carts.size());

        //CleanUp
        cartDao.deleteById(cartId);
        userDao.deleteById(userId);
        productDao.deleteById(productId);
    }

    @Test
    public void deleteByTest() {

        //Given
        Cart cart =new Cart();

        //When
        cartDao.save(cart);
        Long cartId = cart.getId();

        Optional<Cart> cartTest = cartDao.findById(cartId);
        cartDao.deleteById(cartTest.get().getId());

        List<Cart> carts = new ArrayList<>();
        Iterator<Cart> cartList = cartDao.findAll().iterator();
        cartList.forEachRemaining(carts::add);

        //Then
        assertEquals(0,carts.size());
    }

    @Test
    public void contractCartAndProductRelationshipTest(){
        //Given
        Cart cart = new Cart();
        User user = new User("name", "password");
        Product product = new Product("shoes", 110);
        Product product1 = new Product("T-shirt", 50);

        //When
        cart.setUser(user);
        cart.getProductList().add(product);
        cart.getProductList().add(product1);
        user.getCartList().add(cart);
        product.setCart(cart);
        product1.setCart(cart);

        cartDao.save(cart);
        userDao.save(user);
        productDao.save(product);
        productDao.save(product1);

        Cart cartTest = cartDao.findById(cart.getId()).get();

        //Then
        assertEquals("shoes", cartTest.getProductList().get(0).getName());
        assertEquals(110, cartTest.getProductList().get(0).getPrize(), 0.01);
        assertEquals("T-shirt", cartTest.getProductList().get(1).getName());
        assertEquals(50, cartTest.getProductList().get(1).getPrize(), 0.01);

        //Clean Up
        cartDao.deleteById(cart.getId());
        userDao.deleteById(user.getId());

        Product productTest1 = productDao.findById(product.getId()).get();
        Product productTest2 = productDao.findById(product1.getId()).get();

        assertEquals("shoes", productTest1.getName());
        assertEquals(110, productTest1.getPrize(),0.01);

        assertEquals("T-shirt", productTest2.getName());
        assertEquals(50, productTest2.getPrize(),0.01);

        productDao.deleteById(product.getId());
        productDao.deleteById(product1.getId());
    }
}