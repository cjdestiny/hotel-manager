package com.example.hotelmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotelmanager.mapper.RoleMapper;
import com.example.hotelmanager.pojo.Role;
import com.example.hotelmanager.service.RoleService;
import com.example.hotelmanager.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 根据page,pageSize查询role
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageVO<Role> rolePage(int page, int pageSize) {
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = Wrappers.lambdaQuery();
        roleLambdaQueryWrapper.select();
         /* Page<Role> page1 = Page.of(page,pageSize);
          page1 = page(page1,roleLambdaQueryWrapper);   //另外一钟分页查询写法*/

        // 分页参数
        Page<Role> rolePage = new Page<>(Long.valueOf(page), Long.valueOf(pageSize));

        // 调用分页查询
        IPage<Role> roleIPage = roleMapper.selectPage(rolePage, roleLambdaQueryWrapper);

        // 改造返回结果RolePage
        PageVO<Role> roleRolePageVO = new PageVO<Role>()
                .setPage(page)
                .setPageSize(pageSize)
                .setTotal(roleIPage.getTotal())
                .setItemList(roleIPage.getRecords());


        return roleRolePageVO;
    }

    /**
     * role数据插入
     * @param roleName
     */
    @Override
    public void addRole(String roleName) {
        Role role = new Role().setRoleName(roleName);
        roleMapper.insert(role);
    }
    /**
     * 根据id修改数据
     * @param roleId
     * @param roleName
     */
    @Override
    public void updateByRoleId(int roleId, String roleName) {
        lambdaUpdate()
                .eq(Role::getRoleId,roleId)
                .set(roleName != null,Role::getRoleName,roleName)
                .update();
    }
}
