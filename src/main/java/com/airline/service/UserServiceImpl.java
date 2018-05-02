package com.airline.service;

import com.airline.test.UserDao;
import com.airline.test.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public String registerUser(User user) {
        userDao.insertUser(user);
        return "success";
    }
}
