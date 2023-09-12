/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.PermissionMapper;
import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.acl.utils.queryPermissionHelp;
import com.atguigu.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * PermissionServiceImpl
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 7月 06, 2023
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper,Permission>implements PermissionService {

    //获取菜单列表
    @Override
    public List<Permission> queryAllPermission() {
        List<Permission> allPermissionList = baseMapper.selectList(null);
        List<Permission> list = queryPermissionHelp.buildPermission(allPermissionList);
        return list;
    }

    //删除菜单
    @Override
    public void removeAllPermission(Long id) {
        List<Long> idList = new ArrayList<>();
        this.removeChildList(id,idList);
        idList.add(id);
        baseMapper.deleteBatchIds(idList);

    }

    private void removeChildList(Long id, List<Long> idList) {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid,id);
        List<Permission> childList = baseMapper.selectList(wrapper);
        childList.stream().forEach(a->{
            idList.add(a.getId());
            this.removeChildList(a.getId(),idList);
        });
    }
}
