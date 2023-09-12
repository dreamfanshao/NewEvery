/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.product.api;

import com.atguigu.ssyx.model.product.Category;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.product.service.CategoryService;
import com.atguigu.ssyx.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * ProductInnnerController
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 8月 12, 2023
 */
@RestController
@RequestMapping("/api/product")
public class ProductInnnerController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SkuInfoService skuInfoService;

    //根据分类id获取分类信息
    @GetMapping("inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId){
        Category category = categoryService.getById(categoryId);
        return category;
    }

    //根据skuId获取sku信息
    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId){
        return skuInfoService.getById(skuId);
    }

    //根据skuId列表得到sku信息列表
    @PostMapping("inner/findSkuInfoList")
    public List<SkuInfo> findSkuInfoList(@RequestBody List<Long> skuIdList){
        return skuInfoService.findSkuInfoList(skuIdList);
    }

    //根据分类id获取分类列表
    @PostMapping("inner/findCategoryList")
    public List<Category> findCategoryList(@RequestBody List<Long> categoryList){
        return categoryService.listByIds(categoryList);
    }

    //1 根据keyword查询sku匹配内容列表
    @GetMapping("inner/findSkuInfoByKeyword/{keyword}")
    public List<SkuInfo> findSkuInfoByKeyword(@PathVariable("keyword") String keyword){
        return skuInfoService.findSkuInfoByKeyword(keyword);
    }

}
