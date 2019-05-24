package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private boolean keyIsActive = false;
    private String info;
    private boolean accountIsVerified = false;
    private boolean statusIsChanged = false;
    private boolean deleteUser = false;

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

    public UserDto getUser(final UserAccauntDto userAccauntDto) {
        return getUserDto(userAccauntDto);
    }

    public String getKey(final UserAccauntDto userAccauntDto) throws ParseException {
        return generateNewKey(userAccauntDto);
    }

    public String updatePassword(final UpdatedAccount updatedAccount) throws ParseException {
        return createNewPassword(updatedAccount,validateKey(updatedAccount.getUserId()));
    }

    public String changeUserStatus(final ChangeStatusByAdmin changeStatusByAdmin) throws ParseException {
        if (newStatus(changeStatusByAdmin)){
            info = "The status has been changed.";
        }else {
            info = "The status has not been changed.";
        }
        return info;
    }

    public String deleteAccount(final UserAccauntDto userAccauntDto){
        if (deleteUser(userAccauntDto)){
            info = "The user has been removed.";
        }else {
            info = "The user has not been deleted.";
        }
        return info;
    }

    public int createFirstKey(){
        return keyGenerator();
    }

    private boolean deleteUser(final UserAccauntDto userAccauntDto){
        if (validateKey(userAccauntDto.getUserId()) && verifyUserAccount(userAccauntDto.getUserId(), userAccauntDto.getPassword(), userAccauntDto.getKey())){
            userDao.deleteById(userAccauntDto.getUserId());
            deleteUser = true;
        }
        return deleteUser;
    }

    private UserDto getUserDto(final UserAccauntDto userAccauntDto){
        UserDto userDto = new UserDto();
        if(verifyUserAccount(userAccauntDto.getUserId(), userAccauntDto.getPassword(), userAccauntDto.getKey())){
            userDto.setId(userDao.findById(userAccauntDto.getUserId()).get().getId());
            userDto.setUserName(userDao.findById(userAccauntDto.getUserId()).get().getUserName());
            userDto.setStatus(userDao.findById(userAccauntDto.getUserId()).get().isStatus());
            userDto.setUserKey(userDao.findById(userAccauntDto.getUserId()).get().getUserKey());
            userDto.setTimeGenerateKey(dateFormat.format(userDao.findById(userAccauntDto.getUserId()).get().getTimeGenerateKey()));
            userDto.setPassword(userDao.findById(userAccauntDto.getUserId()).get().getPassword());
        }
        return userDto;
    }

    private boolean newStatus(final ChangeStatusByAdmin changeStatusByAdmin) throws ParseException {
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
            statusIsChanged = true;
        }
        return statusIsChanged;

    }
    private String createNewPassword(final UpdatedAccount updatedAccount, boolean activeKey) throws ParseException {
        UserDto userDto = new UserDto();
        if (validateKey(updatedAccount.getUserId()) && verifyUserAccount(updatedAccount.getUserId(), updatedAccount.getNewPassword(), updatedAccount.getKey())){
            userDto.setId(userDao.findById(updatedAccount.getUserId()).get().getId());
            userDto.setUserName(userDao.findById(updatedAccount.getUserId()).get().getUserName());
            userDto.setStatus(userDao.findById(updatedAccount.getUserId()).get().isStatus());
            userDto.setUserKey(userDao.findById(updatedAccount.getUserId()).get().getUserKey());
            userDto.setTimeGenerateKey(dateFormat.format(userDao.findById(updatedAccount.getUserId()).get().getTimeGenerateKey()));
            userDto.setPassword(updatedAccount.getNewPassword());
            userMapper.mapToUserDto(userDao.save(userMapper.mapToUserWithAllParam(userDto)));
            info = "Password update completed successfully.";
        }else {
            info = "Password update failed";
        }
        return info;
    }

    private boolean validateKey(final Long userId){
        Calendar keyExpiredTime = Calendar.getInstance();
        keyExpiredTime.setTime(userDao.findById(userId).get().getTimeGenerateKey());
        keyExpiredTime.add(Calendar.MINUTE, 60);
        if(Calendar.getInstance().before(keyExpiredTime)){
            keyIsActive = true;
        }
        return keyIsActive;
    }

    private boolean verifyUserAccount(final Long userId, final String password, final  int key){
        if (userDao.findById(userId).get().getPassword().equals(password) &&
                userDao.findById(userId).get().getUserKey() == key){
            accountIsVerified = true;
        }
        return accountIsVerified;
    }

    private String generateNewKey(final UserAccauntDto userAccauntDto) throws ParseException {
        User user = userDao.findById(userAccauntDto.getUserId()).get();
        if(verifyUserAccount(userAccauntDto.getUserId(), userAccauntDto.getPassword(), userAccauntDto.getKey())){
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getUserName(),
                    user.isStatus(),
                    keyGenerator(),
                    dateFormat.format(new Date()),
                    user.getPassword(),
                    new ArrayList<CartDto>(),
                    new ArrayList<OrderDto>());
            userMapper.mapToUserDto(userDao.save(userMapper.mapToUserWithAllParam(userDto)));
            info = "Your new key is: " + userDao.findById(userAccauntDto.getUserId()).get().getUserKey();
        }else {
            info = "Bad data has been entered.";
        }
        return info;
    }

    private int keyGenerator(){
        Random random = new Random();
        return random.nextInt(89999)+10001;
    }
}
