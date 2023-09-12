package com.atguigu.ssyx.sys.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.Region;
import com.atguigu.ssyx.sys.service.RegionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-07-06
 */
@RestController
@RequestMapping("/admin/sys/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    //根据区域关键字查询区域信息
    @ApiOperation("关键字查询区域")
    @GetMapping("findRegionByKeyword/{keyword}")
    public Result findRegin(@PathVariable String keyword){
        List<Region> regionList = regionService.findByKeyword(keyword);
        return Result.ok(regionList);
    }


}

