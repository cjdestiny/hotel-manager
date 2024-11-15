package com.example.hotelmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotelmanager.pojo.Role;
import com.example.hotelmanager.vo.RolePageVO;

public interface RoleService extends IService<Role> {
    /**
     * role分页查询
     * @param page
     * @param pageSize
     * @return
     */
    RolePageVO<Role> rolePage(int page, int pageSize);

    /**
     * role数据插入
     * @param roleName
     */
    void addRole(String roleName);
    /**
     * 根据id修改数据
     * @param roleId
     * @param roleName
     */

    void updateByRoleId(int roleId, String roleName);
}
