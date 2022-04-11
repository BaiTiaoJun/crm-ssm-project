package com.zhangshun.crm.settings.service.impl;

import com.zhangshun.crm.settings.service.UserService;
import com.zhangshun.crm.settings.dao.UserMapper;
import com.zhangshun.crm.settings.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.queryAllUsers();
    }


}
