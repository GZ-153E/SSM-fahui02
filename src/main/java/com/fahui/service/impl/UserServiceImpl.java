package com.fahui.service.impl;

import com.fahui.bean.User;
import com.fahui.dao.UserMapper;
import com.fahui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectByOne(User user) {
        User byOne = userMapper.selectByOne(user);
        return byOne;
    }
}
