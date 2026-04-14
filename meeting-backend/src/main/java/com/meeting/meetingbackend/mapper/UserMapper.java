package com.meeting.meetingbackend.mapper;

import com.meeting.meetingbackend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper // 必须加这个注解，Spring 才能将其注入到 Service
public interface UserMapper {

    // 实现简单的登录查询
    @Select("SELECT * FROM sys_user WHERE username = #{username} AND password = #{password}")
    User login(String username, String password);
}