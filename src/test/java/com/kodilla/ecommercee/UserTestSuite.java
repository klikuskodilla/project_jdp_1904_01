package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dao.UserDao;
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

    @Test
    public void saveAndFindByIdTest(){
        //Given
        User user = new User("Name1", "password1");

        //When
        userDao.save(user);
        Long userId = user.getId();
        int userKey = user.getUserKey();
        Date timeKeyGenerate = user.getTimeGenerateKey();
        boolean status = user.isStatus();

        //Then
        assertEquals("Name1", userDao.findById(userId).get().getUserName());
        assertEquals("password1", userDao.findById(userId).get().getPassword());
        assertEquals(userKey, userDao.findById(userId).get().getUserKey());
        assertEquals(timeKeyGenerate, userDao.findById(userId).get().getTimeGenerateKey());
        assertEquals(status, userDao.findById(userId).get().isStatus());

        //Clean Up
        userDao.deleteById(userId);
    }
}
