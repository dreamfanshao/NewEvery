package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface RoleService extends IService<Role> {
    //角色条件分页查询
    IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    //获取所有角色,根据用户id查询用户角色列表
    Map<String, Object> findRoleByAdminId(Long adminId);

    //给某个用户分配角色
    void saveAdminRole(Long adminId, Long[] roleIds);
}
