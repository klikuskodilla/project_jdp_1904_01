package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public List<UserDto> mapToUserDtoList(List<User> users) {
        return null;
    }

    public User mapToUser(UserDto userDto) {
        return null;
    }

    public UserDto mapToUserDto(User user) {
        return null;
    }
}
