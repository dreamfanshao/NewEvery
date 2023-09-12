/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * PermissionController
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 7月 06, 2023
 */
@RestController
@RequestMapping("/admin/acl/permission")
@Api(tags = "菜单管理")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取菜单列表
    @ApiOperation("获取菜单列表")
    @GetMapping
    public Result getPermission(){
        List<Permission> permissionList = permissionService.queryAllPermission();
        return Result.ok(permissionList);
    }

    //保存菜单
    @ApiOperation("保存菜单")
    @PostMapping("save")
    public Result savePermission(@RequestBody Permission permission){
        permissionService.save(permission);
        return Result.ok(null);
    }

    //更新菜单
    @ApiOperation("更新菜单")
    @PutMapping("update")
    public Result updatePermission(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return Result.ok(null);
    }

    //删除菜单
    @ApiOperation("删除菜单")
    @DeleteMapping("remove/{id}")
    public Result removePermission(@PathVariable Long id){
        permissionService.removeAllPermission(id);
        return Result.ok(null);
    }
}
