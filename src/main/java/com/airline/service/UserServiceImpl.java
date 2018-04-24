package com.airline.service;

import com.airline.dao.UserDao;
import com.airline.entity.User;
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
