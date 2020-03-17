package com.tan.springbootshirotest.service.impl;

import com.tan.springbootshirotest.bean.User;
import com.tan.springbootshirotest.mapper.UserMapper;
import com.tan.springbootshirotest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int id) {
        return userMapper.findById(id);
    }

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        return userMapper.findByName(name);
    }
}
