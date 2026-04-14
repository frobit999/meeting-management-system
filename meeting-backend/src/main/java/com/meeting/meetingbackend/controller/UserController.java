package com.meeting.meetingbackend.controller;

import com.meeting.meetingbackend.common.Result;
import com.meeting.meetingbackend.entity.User;
import com.meeting.meetingbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // 声明这是一个 RESTful 控制器 [cite: 30, 60]
@RequestMapping("/user") // 接口路径前缀
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login") // 登录通常使用 POST 请求 [cite: 85]
    public Result login(@RequestBody User user) {
        // 调用 Service 获取登录结果
        User loginUser = userService.login(user.getUsername(), user.getPassword());

        if (loginUser != null) {
            // 登录成功，返回用户信息 [cite: 30]
            // 注意：实际开发通常会返回一个 Token [cite: 59]，咱们这里先按基础逻辑走
            return Result.success(loginUser);
        } else {
            // 登录失败，返回错误提示 [cite: 30]
            return Result.error("用户名或密码错误");
        }
    }
}