/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.RoleMapper;
import com.atguigu.ssyx.acl.service.AdminRoleService;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * RoleServiceImpl
 * </p>
 *
 * @author 繁少
 * @Version: 1.0
 * @since 7月 04, 2023
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;

    //角色条件分页查询
    @Override
    public IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //获取条件值
        String roleName = roleQueryVo.getRoleName();
        //创建mp对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        //判断条件是否为空，不为空封装条件
        if(!StringUtils.isEmpty(roleName)){
            wrapper.like(Role::getRoleName,roleName);
        }
        //调用方法实现分页查询
        Page<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);
        //返回page对象

        return rolePage;
    }

    //获取所有角色,根据用户id查询用户角色列表
    @Override
    public Map<String, Object> findRoleByAdminId(Long adminId) {
        //查询所有角色
        List<Role> allRoleList = baseMapper.selectList(null);

        //2.1根据用户id查询 用户角色关系表 admin_role 查询用户分配角色id列表
        // List<AdminRole>
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,adminId);
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);
        //2.2 通过第一步返回集合，获取所有角色id的列表 List<Long)
        List<Long> longList = adminRoleList.stream()
                                    .map(adminRole -> adminRole.getRoleId())
                                    .collect(Collectors.toList());
        //2.3创建新的ist集合，用于存储用户配置角色
        ArrayList<Role> roleList = new ArrayList<>();
        //2.4 遍历所有角色列表 aLLRolesList，得到每个角色
        // 判断所有角色里面是否包含已经分配角色id，封装到2.3新的ist集合
        for (Role role:allRoleList) {
            if(longList.contains(role.getId())){
                roleList.add(role);
            }
            
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("assignRoles",roleList);
        map.put("allRolesList",allRoleList);
        return map;
    }

    //给某个用户分配角色
    @Override
    public void saveAdminRole(Long adminId, Long[] roleIds) {
        //删除角色列表
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,adminId);
        adminRoleService.remove(wrapper);

        //分配角色
        ArrayList<AdminRole> arrayList = new ArrayList<>();
        for (Long roleId:roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            arrayList.add(adminRole);
        }
        adminRoleService.saveBatch(arrayList);

    }
}
