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

    public User mapToUserWithAllParam(final GetUserDto getUserDto) throws ParseException {
        return new User(
                getUserDto.getId(),
                getUserDto.getUserName(),
                getUserDto.isStatus(),
                getUserDto.getUserKey(),
                dateFormat.parse(getUserDto.getTimeGenerateKey()),
                getUserDto.getPassword(),
                new ArrayList<Cart>(),
                new ArrayList<Order>());
    }

    public List<UserAccountDto> getUsersDtoList(List<User> userList){
        return userList.stream()
                .map(u -> new UserAccountDto(
                        u.getId(),
                        u.getUserName(),
                        u.getUserKey(),
                        u.isStatus()))
                .collect(Collectors.toList());
    }

    public GetUserDto mapToUserDto(final User user){
        return new GetUserDto(
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
