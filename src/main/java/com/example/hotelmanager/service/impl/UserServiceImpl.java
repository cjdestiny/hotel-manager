package com.example.hotelmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotelmanager.mapper.RoleMapper;
import com.example.hotelmanager.mapper.UserMapper;
import com.example.hotelmanager.pojo.Role;
import com.example.hotelmanager.pojo.User;
import com.example.hotelmanager.service.UserService;
import com.example.hotelmanager.vo.RolePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

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
     * 根据page,pageSize查询role
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public RolePageVO<Role> rolePage(int page, int pageSize) {
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = Wrappers.lambdaQuery();
        roleLambdaQueryWrapper.select();
         /* Page<Role> page1 = Page.of(page,pageSize);
          page1 = page(page1);   //另外一钟分页查询写法*/

        // 分页参数
        Page<Role> rolePage = new Page<>(Long.valueOf(page), Long.valueOf(pageSize));

        // 调用分页查询
        IPage<Role> roleIPage = roleMapper.selectPage(rolePage, roleLambdaQueryWrapper);

        // 改造返回结果RolePage
        RolePageVO<Role> roleRolePageVO = new RolePageVO<Role>()
                .setPage(page)
                .setPageSize(pageSize)
                .setTotal(roleIPage.getTotal())
                .setItemList(roleIPage.getRecords());


        return roleRolePageVO;
    }

}
