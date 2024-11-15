package com.example.hotelmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotelmanager.mapper.UserMapper;
import com.example.hotelmanager.pojo.User;
import com.example.hotelmanager.service.UserService;
import com.example.hotelmanager.vo.PageVO;
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

    /**
     * 查询用户管理列表.roleId=0,查询全部
     * @param page
     * @param pageSize
     * @param roleId
     * @return
     */
    @Override
    public PageVO<User> pageUserList(int page, int pageSize, int roleId) {
        //wrapper构造条件
        LambdaQueryWrapper<User> userLambdaQueryWrapper= Wrappers.lambdaQuery();

        if(roleId == 0){
            //roleId为0,没有条件，直接查全部
            userLambdaQueryWrapper.select();
        }else{
            //否则按roleId的条件查
            userLambdaQueryWrapper.eq(User::getRoleId,roleId);
        }
        // 分页参数
        Page<User> userPage = new Page<>(page,pageSize);
        // 调用分页查询
        IPage<User> userIPage = userMapper.selectPage(userPage,userLambdaQueryWrapper);
        //构造返回结果
        PageVO<User> userPageVO = new PageVO<User>()
                .setPage(page)
                .setPageSize(pageSize)
                .setTotal(userIPage.getTotal())
                .setItemList(userIPage.getRecords());
        return userPageVO;
    }


}
