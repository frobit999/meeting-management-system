package com.meeting.meetingbackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data // 这个注解就是 Lombok 提供的，自动生成代码
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer role; // 0:管理员, 1:普通用户 [cite: 59, 61]
    private LocalDateTime createTime;
}