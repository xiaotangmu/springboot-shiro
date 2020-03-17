package com.tan.springbootshirotest.mapper;

import com.tan.springbootshirotest.bean.User;

public interface UserMapper {
    public User findByName(String name);
    public User findById(int id);
}
