package com.duoqu.community.service;

import com.duoqu.community.mapper.UserMapper;
import com.duoqu.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public int addUser(User user){
        return userMapper.addUser(user);
    }

    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }
}
