package com.example.hotelmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotelmanager.pojo.User;
import com.example.hotelmanager.vo.PageVO;

public interface UserService extends IService<User> {
    /**
     * 根据usename查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 查询用户管理列表
     * @param page
     * @param pageSize
     * @param roleId
     * @return
     */

    PageVO<User> pageUserList(int page, int pageSize, int roleId);
}
