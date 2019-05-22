package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UsersDto> getUsersDto() {
        return userMapper.getUsersDtoList(userService.getUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestBody AccauntDto accauntDto) {
        return userService.getUser(accauntDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public void createUser(@RequestBody CreateUserDto createUserDto){
        userService.saveUser(userMapper.mapToUser(createUserDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "generateNewKey")
    public String generateNewKey(@RequestBody AccauntDto accauntDto) throws ParseException {
        return userService.getKey(accauntDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUserPassword")
    public String updateUserPassword(@RequestBody UpDateAccount upDateAccount) throws ParseException {
        return userService.updatePassword(upDateAccount);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "changeUserStatus")
    public String changeUserStatus(@RequestBody ChangeStatusByAdmin changeStatusByAdmin) throws ParseException {
        return userService.changeUserStatus(changeStatusByAdmin);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public void updateUser(UserDto userDto){
        System.out.println("Update user");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public String deleteUser(@RequestBody AccauntDto accauntDto){
        return userService.deleteAccount(accauntDto);
    }
}
