package com.atguigu.ssyx.sys.service.impl;


import com.atguigu.ssyx.model.sys.Region;
import com.atguigu.ssyx.sys.mapper.RegionMapper;
import com.atguigu.ssyx.sys.service.RegionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-07-06
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    //根据区域关键字查询区域信息
    @Override
    public List<Region> findByKeyword(String keyword) {
        LambdaQueryWrapper<Region> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            wrapper.like(Region::getName,keyword);
        }
        List<Region> regionList = baseMapper.selectList(wrapper);
        return regionList;
    }
}
