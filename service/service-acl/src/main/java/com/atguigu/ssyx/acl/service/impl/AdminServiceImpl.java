/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.AdminMapper;
import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * AdminServiceImpl
 * </p>
 *
 * @author wangff
 * @Version: 1.0
 * @since 7月 05, 2023
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    //用户列表
    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo adminQueryVo) {
        //获取条件值
        String username = adminQueryVo.getUsername();
        String name = adminQueryVo.getName();
        //创建mp对象
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        //判断条件是否为空，不为空封装条件
        if(!StringUtils.isEmpty(username)){
            wrapper.eq(Admin::getUsername,username);
        }
        if(!StringUtils.isEmpty(name)){
            wrapper.like(Admin::getName,name);
        }
        //调用方法实现分页查询
        Page<Admin> adminPage = baseMapper.selectPage(pageParam, wrapper);
        //返回page对象
        return adminPage;
    }
}
