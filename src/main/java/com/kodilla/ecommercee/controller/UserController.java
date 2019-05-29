package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserAccountDto> getUsersDto() {
        return userService.getUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public GetUserDto getUser(@RequestBody UserAccountDto userAccountDto) throws UserNotFoundException {
        return userService.getUser(userAccountDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public void createUser(@RequestBody CreateUserDto createUserDto){
        userService.saveUser(createUserDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "generateNewKey")
    public String generateNewKey(@RequestBody UserAccountDto userAccountDto) throws ParseException, UserNotFoundException {
        return userService.getKey(userAccountDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUserPassword")
    public String updateUserPassword(@RequestBody UpdatedAccount upDateAccount) throws ParseException, UserNotFoundException {
        return userService.updatePassword(upDateAccount);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "changeUserStatus")
    public String changeUserStatus(@RequestBody ChangeStatusByAdmin changeStatusByAdmin) throws ParseException, UserNotFoundException {
        return userService.changeUserStatus(changeStatusByAdmin);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public void updateUser(GetUserDto getUserDto){
        System.out.println("Update user");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public String deleteUser(@RequestBody UserAccountDto userAccountDto){
        return userService.deleteAccount(userAccountDto);
    }
}
