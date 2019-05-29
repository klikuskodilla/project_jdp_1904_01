package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    public User saveUser(final CreateUserDto createUserDto){
        User user = new User(createUserDto.getUserName(), createUserDto.getPassword());
        user.setUserKey(keyGenerator());
        return userDao.save(user);
    }

    public List<UserAccountDto> getUsers(){
        return userMapper.getUsersDtoList(userDao.findAll());
    }

    public GetUserDto getUser(final UserAccountDto userAccountDto) throws UserNotFoundException {
        return getUserDto(userAccountDto);
    }

    public String getKey(final UserAccountDto userAccountDto) throws ParseException, UserNotFoundException {
        return setNewKeyForUser(userAccountDto);
    }

    public String updatePassword(final UpdatedAccount updatedAccount) throws ParseException, UserNotFoundException {
        return createNewPassword(updatedAccount);
    }

    public String changeUserStatus(final ChangeStatusByAdmin changeStatusByAdmin) throws ParseException, UserNotFoundException {
        if (newStatus(changeStatusByAdmin)){
            return  "The status has been changed.";
        }
        return "The status has not been changed.";
    }

    public String deleteAccount(final UserAccountDto userAccountDto){
        if (deleteUser(userAccountDto)){
            return "The user has been removed.";
        }
        return "The user has not been deleted.";
    }

    public int createFirstKey(){
        return keyGenerator();
    }

    private boolean deleteUser(final UserAccountDto userAccountDto){
        if (validateKey(userAccountDto.getUserId()) && verifyUserAccount(userAccountDto.getUserId(), userAccountDto.getPassword(), userAccountDto.getKey())){
            userDao.deleteById(userAccountDto.getUserId());
            return true;
        }
        return false;
    }

    private GetUserDto getUserDto(final UserAccountDto userAccountDto) throws UserNotFoundException {
        if(verifyUserAccount(userAccountDto.getUserId(), userAccountDto.getPassword(), userAccountDto.getKey())){
            return userMapper.mapToUserDto(userDao.findById(userAccountDto.getUserId()).orElseThrow(UserNotFoundException::new));

        }
        return new GetUserDto();
    }

    private boolean newStatus(final ChangeStatusByAdmin changeStatusByAdmin) throws ParseException, UserNotFoundException {
        if(changeStatusByAdmin.getAdminLogin().equals("admin") && changeStatusByAdmin.getAdminPassword().equals("admin")){
            GetUserDto getUserDto =
                    userMapper.mapToUserDto(userDao.findById(changeStatusByAdmin.getUserId()).orElseThrow(UserNotFoundException::new));
            getUserDto.setStatus(changeStatusByAdmin.isNewStatus());
            userDao.save(userMapper.mapToUserWithAllParam(getUserDto));
            return true;
        }
        return false;
    }

    private String createNewPassword(final UpdatedAccount updatedAccount) throws ParseException, UserNotFoundException {
        if (validateKey(updatedAccount.getUserId()) && verifyUserAccount(updatedAccount.getUserId(), updatedAccount.getPassword(), updatedAccount.getKey())){
            GetUserDto getUserDtoWithNewPassword =
                    userMapper.mapToUserDto(userDao.findById(updatedAccount.getUserId()).orElseThrow(UserNotFoundException::new));
            getUserDtoWithNewPassword.setPassword(updatedAccount.getNewPassword());
            userDao.save(userMapper.mapToUserWithAllParam(getUserDtoWithNewPassword));
            return "Password update completed successfully.";
        }
        return "Password update failed";
    }

    private boolean validateKey(final Long userId){
        Calendar keyExpiredTime = Calendar.getInstance();
        keyExpiredTime.setTime(userDao.findById(userId).get().getTimeGenerateKey());
        keyExpiredTime.add(Calendar.MINUTE, 60);
        if(Calendar.getInstance().before(keyExpiredTime)){
            return true;
        }
        return false;
    }

    private boolean verifyUserAccount(final Long userId, final String password, final  int key){
        if (userDao.findById(userId).get().getPassword().equals(password) &&
                userDao.findById(userId).get().getUserKey() == key){
            return true;
        }
        return false;
    }

    private String setNewKeyForUser(final UserAccountDto userAccountDto) throws ParseException, UserNotFoundException {
        if(verifyUserAccount(userAccountDto.getUserId(), userAccountDto.getPassword(), userAccountDto.getKey())){
            GetUserDto getUserDto =
                    userMapper.mapToUserDto(userDao.findById(userAccountDto.getUserId()).orElseThrow(UserNotFoundException::new));
            getUserDto.setUserKey(keyGenerator());
            userDao.save(userMapper.mapToUserWithAllParam(getUserDto));
            return  "Your new key is: " + userDao.findById(userAccountDto.getUserId()).get().getUserKey();
        }
        return "Bad data has been entered.";
    }

    private int keyGenerator(){
        Random random = new Random();
        return random.nextInt(89999)+10001;
    }
}
