package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDao userDao;

    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserDtoList(userDao.findAll());
    }

    public UserDto getUser(Long id) {
        return userMapper.mapToUserDto(userDao.findById(id).orElse(new User()));
    }

    public User save(UserDto orderDto) {
        return userDao.save(userMapper.mapToUser(orderDto));
    }

    public void deleteOrderById(Long id) {
        userDao.deleteById(id);
    }
}
