package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getUserName(),
                userDto.getPassword()
        );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.isStatus(),
                user.getUserKey(),
                user.getPassword()
        );
    }
}
