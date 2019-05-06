package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.user.UserDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(Long userId){
        return new UserDto(1L, "name", true, 11111);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public void createUser(UserDto userDto){
        System.out.println("New User Created");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public void updateUser(UserDto userDto){
        System.out.println("Update user");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(Long userId){
        System.out.println("Delete user with number Id: " + userId);
    }
}
