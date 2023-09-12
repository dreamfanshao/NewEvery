package com.atguigu.ssyx.sys.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.sys.service.RegionWareService;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-07-06
 */
@Api(tags = "城市区域管理")
@RestController
@RequestMapping("/admin/sys/regionWare")

public class RegionWareController {

    @Autowired
    private RegionWareService regionWareService;

    //开通区域列表
    @ApiOperation("开通区域列表")
    @GetMapping("{page}/{limit}")
    public Result wareList(@PathVariable Long page,
                           @PathVariable Long limit,
                           RegionWareQueryVo regionWareQueryVo){
        Page<RegionWare> pageParam = new Page<>(page, limit);
        IPage<RegionWare> pageModel = regionWareService.pageList(pageParam,regionWareQueryVo);
        return Result.ok(pageModel);
    }

    //添加开通区域
    @ApiOperation("添加开通区域")
    @PostMapping("save")
    public Result saveRegionWare(@RequestBody RegionWare regionWare){
        regionWareService.saveWare(regionWare);
        return Result.ok(null);
    }



}

