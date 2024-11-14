package com.example.hotelmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotelmanager.pojo.User;

public interface UserService extends IService<User> {
    /**
     * 根据usename查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
