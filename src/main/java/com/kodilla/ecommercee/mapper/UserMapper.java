package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public User mapToUser(final CreateUserDto createUserDto){
        return new User(
                createUserDto.getUserName(),
                createUserDto.getPassword()
        );
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
                        u.getUserName()))
                .collect(Collectors.toList());
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.isStatus(),
                user.getUserKey(),
                dateFormat.format(user.getTimeGenerateKey()),
                user.getPassword());
    }
}
