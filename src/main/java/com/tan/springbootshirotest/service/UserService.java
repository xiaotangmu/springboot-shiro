package com.tan.springbootshirotest.service;

import com.tan.springbootshirotest.bean.User;

public interface UserService {

    public User getUserByName(String name);

    public User getUserById(int id);
}
