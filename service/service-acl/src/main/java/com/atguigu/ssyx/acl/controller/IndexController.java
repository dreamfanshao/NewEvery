/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * IndexController
 * </p>
 *
 * @author 繁少
 * @Version: 1.0
 * @since 7月 02, 2023
 */
@Api(tags = "登录接口")
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {
    //1 login登录
    @ApiOperation("登录接口")
    @PostMapping("login")
    public Result login(){
        //返回token值，前端要求
        Map<String,String> map = new HashMap<>();
        map.put("token","token-admin");
        return Result.ok(map);
    }
    //2 getInfo 获取信息
    @ApiOperation("获取信息接口")
    @GetMapping("info")
    public Result info(){
        Map<String,String> map = new HashMap<>();
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    //3 logout 退出
    @ApiOperation("退出接口")
    @PostMapping("logout")
    public Result logout(){
        return Result.ok(null);
    }

}
