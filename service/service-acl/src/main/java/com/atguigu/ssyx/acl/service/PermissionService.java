package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PermissionService extends IService<Permission> {

    //获取菜单列表
    List<Permission> queryAllPermission();

    //删除菜单
    void removeAllPermission(Long id);
}
