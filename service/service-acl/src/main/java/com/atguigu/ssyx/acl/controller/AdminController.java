/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.util.MD5;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * AdminController
 * </p>
 *
 * @author wangff
 * @Version: 1.0
 * @since 7月 05, 2023
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/admin/acl/user")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    //获取所有角色,根据用户id查询用户角色列表
    @ApiOperation("获取用户角色")
    @GetMapping("toAssign/{adminId}")
    public Result toAssign(@PathVariable Long adminId){
        Map<String,Object> roleMap = roleService.findRoleByAdminId(adminId);
        return Result.ok(roleMap);
    }

    //给某个用户分配角色
    @ApiOperation("用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long adminId,@RequestParam Long[] roleId){
        roleService.saveAdminRole(adminId,roleId);
        return Result.ok(null);

    }

    //用户列表
    @ApiOperation("条件分页查询")
    @GetMapping("{current}/{limit}")
    public Result pageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           AdminQueryVo adminQueryVo){
        //创建page对象
        Page<Admin> pageParam = new Page<>(current,limit);
        //调用service方法实现分页查询，返回分页对象
        IPage<Admin> pageModel = adminService.selectPage(pageParam,adminQueryVo);
        return Result.ok(pageModel);

    }
    //根据id查询用户
    @ApiOperation("根据id查询用户")
    @GetMapping("get/{id}")
    public Result getById(@PathVariable Long id){
        Admin admin = adminService.getById(id);
        return Result.ok(admin);
    }
    //添加用户
    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result saveAdmin(@RequestBody Admin user){
        //获取密码
        String password = user.getPassword();
        //密码加密
        String passwordMD5 = MD5.encrypt(password);
        //保存密码
        user.setPassword(passwordMD5);
        adminService.save(user);
        return Result.ok(null);

    }
    //修改用户
    @ApiOperation("修改用户")
    @PutMapping("update")
    public Result updateAdmin(@RequestBody Admin user){
        adminService.updateById(user);
        return Result.ok(null);
    }
    //根据id删除
    @ApiOperation("根据id删除")
    @DeleteMapping("remove/{id}")
    public Result removeById(@PathVariable Long id){
        adminService.removeById(id);
        return Result.ok(null);
    }
    //批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> longList){
        adminService.removeByIds(longList);
        return Result.ok(null);
    }
}
