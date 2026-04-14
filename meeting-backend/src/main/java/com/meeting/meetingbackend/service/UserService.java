package com.meeting.meetingbackend.service;

import com.meeting.meetingbackend.entity.User;
import com.meeting.meetingbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 告诉 Spring 这是一个业务逻辑类
public class UserService {

    @Autowired // 自动把刚才写的 Mapper 注入进来
    private UserMapper userMapper;

    // 登录的业务逻辑：直接调用 mapper 去数据库查
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }
}