package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private UserService userService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public User mapToUser(final CreateUserDto createUserDto){
        User user = new User(
                createUserDto.getUserName(),
                createUserDto.getPassword()
        );
        user.setUserKey(userService.createFirstKey());
        return user;
    }

    public User mapToUserWithAllParam(final UserDto userDto) throws ParseException {
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.isStatus(),
                userDto.getUserKey(),
                dateFormat.parse(userDto.getTimeGenerateKey()),
                userDto.getPassword(),
                new ArrayList<Cart>(),
                new ArrayList<Order>());
    }

    public List<UsersDto> getUsersDtoList(List<User> userList){
        return userList.stream()
                .map(u -> new UsersDto(
                        u.getId(),
                        u.getUserName(),
                        u.isStatus(),
                        u.getUserKey()))
                .collect(Collectors.toList());
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.isStatus(),
                user.getUserKey(),
                dateFormat.format(user.getTimeGenerateKey()),
                user.getPassword(),
                new ArrayList<CartDto>(),
                new ArrayList<OrderDto>());
    }
}
