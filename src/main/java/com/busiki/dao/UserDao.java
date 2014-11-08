package com.busiki.dao;

import com.busiki.model.User;

public interface UserDao  {
    void create(User user,String password);
    User findByEmail(String email);
}