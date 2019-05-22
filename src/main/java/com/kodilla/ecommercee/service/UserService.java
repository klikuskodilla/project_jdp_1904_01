package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private boolean activeKey = false;
    private String info;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    public User saveUser(final User user){
        return userDao.save(user);
    }

    public List<User> getUsers(){
        return userDao.findAll();
    }

    public UserDto getUser(final AccauntDto accauntDto) {
       return getUserDto(accauntDto);
    }

    public String getKey(final AccauntDto accauntDto) throws ParseException {
        return generateNewKey(accauntDto);
    }

    public String updatePassword(final UpDateAccount upDateAccount) throws ParseException {
        return newPassword(upDateAccount,validityKey(upDateAccount));
    }

    public String changeUserStatus(final ChangeStatusByAdmin changeStatusByAdmin) throws ParseException {
        if (newStatus(changeStatusByAdmin)){
            info = "The status has been changed.";
        }else {
            info = "The status has not been changed.";
        }
        return info;
    }

    public String deleteAccount(final AccauntDto accauntDto){
        return info;
    }

    private UserDto getUserDto(final AccauntDto accauntDto){
        UserDto userDto = new UserDto();
        if(checkAccount(accauntDto)){
            userDto.setId(userDao.findById(accauntDto.getUserId()).get().getId());
            userDto.setUserName(userDao.findById(accauntDto.getUserId()).get().getUserName());
            userDto.setStatus(userDao.findById(accauntDto.getUserId()).get().isStatus());
            userDto.setUserKey(userDao.findById(accauntDto.getUserId()).get().getUserKey());
            userDto.setTimeGenerateKey(dateFormat.format(userDao.findById(accauntDto.getUserId()).get().getTimeGenerateKey()));
            userDto.setPassword(userDao.findById(accauntDto.getUserId()).get().getPassword());
            userDto.setCartDtoList(new ArrayList<CartDto>());
            userDto.setOrderDtoList(new ArrayList<OrderDto>());
        }
        return userDto;
    }

    private boolean newStatus(final ChangeStatusByAdmin changeStatusByAdmin) throws ParseException {
        boolean changeStatus = false;
        if(changeStatusByAdmin.getAdminLogin().equals("admin") && changeStatusByAdmin.getAdminPassword().equals("admin")){
            UserDto userDto = new UserDto(
                    userDao.findById(changeStatusByAdmin.getUserId()).get().getId(),
                    userDao.findById(changeStatusByAdmin.getUserId()).get().getUserName(),
                    changeStatusByAdmin.isNewStatus(),
                    userDao.findById(changeStatusByAdmin.getUserId()).get().getUserKey(),
                    dateFormat.format(userDao.findById(changeStatusByAdmin.getUserId()).get().getTimeGenerateKey()),
                    userDao.findById(changeStatusByAdmin.getUserId()).get().getPassword(),
                    new ArrayList<CartDto>(),
                    new ArrayList<OrderDto>());
            userMapper.mapToUserDto(userDao.save(userMapper.mapToUserWithAllParam(userDto)));
            changeStatus = true;
        }
        return changeStatus;

    }
    private String newPassword(final UpDateAccount upDateAccount, boolean activeKey) throws ParseException {
        UserDto userDto = new UserDto();
        if (validityKey(upDateAccount)){
            userDto.setId(userDao.findById(upDateAccount.getUserId()).get().getId());
            userDto.setUserName(userDao.findById(upDateAccount.getUserId()).get().getUserName());
            userDto.setStatus(userDao.findById(upDateAccount.getUserId()).get().isStatus());
            userDto.setUserKey(userDao.findById(upDateAccount.getUserId()).get().getUserKey());
            userDto.setTimeGenerateKey(dateFormat.format(userDao.findById(upDateAccount.getUserId()).get().getTimeGenerateKey()));
            userDto.setPassword(upDateAccount.getNewPassword());
            userDto.setCartDtoList(new ArrayList<CartDto>());
            userDto.setOrderDtoList(new ArrayList<OrderDto>());
            userMapper.mapToUserDto(userDao.save(userMapper.mapToUserWithAllParam(userDto)));
            info = "Password update completed successfully.";
        }else {
            info = "Password update failed";
        }
        return info;
    }

    private boolean validityKey(UpDateAccount upDateAccount){
        UserDto userDto = new UserDto();
        Calendar finshKeyValidity = Calendar.getInstance();
        finshKeyValidity.setTime(userDao.findById(upDateAccount.getUserId()).get().getTimeGenerateKey());
        finshKeyValidity.add(Calendar.MINUTE, 60);
        if(userDao.findById(upDateAccount.getUserId()).get().getPassword().equals(upDateAccount.getPassword()) &&
                userDao.findById(upDateAccount.getUserId()).get().isStatus() == true &&
                Calendar.getInstance().before(finshKeyValidity)){
                activeKey = true;
        }
        System.out.println("boolean : " + activeKey);
        return activeKey;
    }

    private boolean checkAccount(AccauntDto accauntDto){
        boolean checkAccount = false;
        if (userDao.findById(accauntDto.getUserId()).get().getPassword().equals(accauntDto.getPassword()) &&
                userDao.findById(accauntDto.getUserId()).get().getUserKey() == accauntDto.getKey()){
            checkAccount = true;
        }
        return checkAccount;
    }

    private String generateNewKey(final AccauntDto accauntDto) throws ParseException {
        User user = userDao.findById(accauntDto.getUserId()).get();
        if(checkAccount(accauntDto)){
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getUserName(),
                    user.isStatus(),
                    user.keyGenerator(),
                    dateFormat.format(new Date()),
                    user.getPassword(),
                    new ArrayList<CartDto>(),
                    new ArrayList<OrderDto>());
            userMapper.mapToUserDto(userDao.save(userMapper.mapToUserWithAllParam(userDto)));
            info = "Your new key is: " + userDao.findById(accauntDto.getUserId()).get().getUserKey();
        }else {
            info = "Bad data has been entered.";
        }
        return info;
    }
}
