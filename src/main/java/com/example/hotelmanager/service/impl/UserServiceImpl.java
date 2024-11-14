package com.example.hotelmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotelmanager.mapper.UserMapper;
import com.example.hotelmanager.pojo.User;
import com.example.hotelmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        //自定义条件构造器，select * from ev_user where username = ?
        //方法1new 一个 Wrapper
            /*LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getUsername,username);*/
        //方法2直接构造
        User user = lambdaQuery()
                .eq(User::getUsername,username)
                .one();
        return user;
    }
}
