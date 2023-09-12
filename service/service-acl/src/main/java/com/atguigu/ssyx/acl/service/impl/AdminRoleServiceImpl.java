/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.AdminRoleMapper;
import com.atguigu.ssyx.acl.service.AdminRoleService;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * AdminRoleServiceImpl
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 7月 05, 2023
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
}
