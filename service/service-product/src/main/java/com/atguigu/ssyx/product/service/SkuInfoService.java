package com.atguigu.ssyx.product.service;


import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.vo.product.SkuInfoQueryVo;
import com.atguigu.ssyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-07-23
 */
public interface SkuInfoService extends IService<SkuInfo> {

    //获取sku分页列表
    IPage<SkuInfo> selectPage(Page<SkuInfo> pageParam, SkuInfoQueryVo skuInfoQueryVo);

    //添加商品
    void saveSkuInfo(SkuInfoVo skuInfoVo);

    //获取商品
    SkuInfoVo getSkuInfoVo(Long id);

    //修改商品
    void updateSkuInfo(SkuInfoVo skuInfoVo);

    //商品审核
    void check(Long skuId, Integer status);

    //商品上架
    void publish(Long skuId, Integer status);

    //新人专享
    void isNewPerson(Long skuId, Integer status);

    //根据skuId列表得到sku信息列表
    List<SkuInfo> findSkuInfoList(List<Long> skuIdList);

    List<SkuInfo> findSkuInfoByKeyword(String keyword);
}
