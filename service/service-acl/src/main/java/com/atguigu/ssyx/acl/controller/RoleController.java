/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * RoleController
 * </p>
 *
 * @author 繁少
 * @Version: 1.0
 * @since 7月 04, 2023
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/acl/role")

public class RoleController {

    @Autowired
    private RoleService roleService;

    //获取角色分页列表
    @ApiOperation("获取角色分页列表")
    @GetMapping("{current}/{limit}")
    public Result pageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           RoleQueryVo roleQueryVo){
        //创建page对象
        Page<Role> pageParam = new Page<>(current, limit);
        //调用service方法实现分页查询，返回分页对象
        IPage<Role> pageModel = roleService.selectPage(pageParam,roleQueryVo);
        return  Result.ok(pageModel);
    }

    //保存一个新角色
    //json数据需要用@RequestBody
    @ApiOperation("保存一个新角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role){
        roleService.save(role);
        return Result.ok(null);
    }

    //获取某个角色
    @ApiOperation("获取某个角色")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    //更新一个角色
    @ApiOperation("更新一个角色")
    @PutMapping("update")
    public Result updateById(@RequestBody Role role){
        roleService.updateById(role);
        return Result.ok(null);
    }

    //删除某个角色
    @ApiOperation("删除某个角色")
    @DeleteMapping("remove/{id}")
    public Result deleteById(@PathVariable Long id){
        roleService.removeById(id);
        return Result.ok(null);
    }

    //批量删除多个角色
    //json的数组，是java的集合
    @ApiOperation("批量删除多个角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        roleService.removeByIds(idList);
        return Result.ok(null);
    }
}
