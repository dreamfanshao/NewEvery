package com.atguigu.ssyx.activit.service.impl;

import com.atguigu.ssyx.activit.mapper.ActivityInfoMapper;
import com.atguigu.ssyx.activit.mapper.ActivityRuleMapper;
import com.atguigu.ssyx.activit.mapper.ActivitySkuMapper;
import com.atguigu.ssyx.activit.service.ActivityInfoService;
import com.atguigu.ssyx.client.product.ProductFeignClient;
import com.atguigu.ssyx.model.activity.ActivityInfo;
import com.atguigu.ssyx.model.activity.ActivityRule;
import com.atguigu.ssyx.model.activity.ActivitySku;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.vo.activity.ActivityRuleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 活动表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-08-15
 */
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo> implements ActivityInfoService {

    @Autowired
    private ActivityRuleMapper activityRuleMapper;

    @Autowired
    private ActivitySkuMapper activitySkuMapper;

    @Autowired
    private ProductFeignClient productFeignClient;

    //优惠活动列表方法
    @Override
    public IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam) {
        Page<ActivityInfo> activityInfoPage = baseMapper.selectPage(pageParam, null);
        //分页查询对象里获取列表数据
        List<ActivityInfo> activityInfoList = activityInfoPage.getRecords();
        //遍历activityInfoList集合，得到每个activityInfo对象
        //向ActivityInfo对象封装活动类型到activityTypeString属性里面
        activityInfoList.stream().forEach(item ->{
            item.setActivityTypeString(item.getActivityType().getComment());
        });
        return activityInfoPage;
    }

    @Override
    public Map<String, Object> findActivityRuleList(Long id) {
        HashMap<String, Object> result = new HashMap<>();
        //1根据活动id查询，查询规则列表activity_rule表
        LambdaQueryWrapper<ActivityRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ActivityRule::getActivityId,id);
        List<ActivityRule> activityRuleList = activityRuleMapper.selectList(wrapper);
        result.put("activityRuleList",activityRuleList);

        //2 根据活动id查询，查询使用规则商品skuid列表activity_sku表
        List<ActivitySku> activitySkuList =
            activitySkuMapper.selectList(new LambdaQueryWrapper<ActivitySku>().eq(ActivitySku::getActivityId, id));
        //获取所有skuId
        List<Long> skuIdList = activitySkuList.stream().map(ActivitySku::getSkuId).collect(Collectors.toList());
        //2.1 通过远程调用service-product模块接口，根据skuid列表得到商品信息
        List<SkuInfo> skuInfoList = productFeignClient.findSkuInfoList(skuIdList);
        result.put("skuInfoList",skuInfoList);
        return result;
    }

    @Override
    public void saveActivityRule(ActivityRuleVo activityRuleVo) {
        //1 根据活动id删除之前已存在
        //activityRule数据删除
        Long activityId = activityRuleVo.getActivityId();
        activityRuleMapper.delete(new LambdaQueryWrapper<ActivityRule>()
            .eq(ActivityRule::getActivityId,activityId));
        //activitySku数据删除
        activitySkuMapper.delete(new LambdaQueryWrapper<ActivitySku>()
            .eq(ActivitySku::getActivityId,activityId));
        //2 获取活动规则列表数据
        List<ActivityRule> activityRuleList = activityRuleVo.getActivityRuleList();
        ActivityInfo activityInfo = baseMapper.selectById(activityId);
        for (ActivityRule activityRule:activityRuleList){
            activityRule.setActivityId(activityId);
            activityRule.setActivityType(activityInfo.getActivityType());
            activityRuleMapper.insert(activityRule);
        }
        //3 获取活动范围数据
        List<ActivitySku> activitySkuList = activityRuleVo.getActivitySkuList();
        for (ActivitySku activitySku:activitySkuList){
            activitySku.setActivityId(activityId);
            activitySkuMapper.insert(activitySku);
        }
    }

    @Override
    public List<SkuInfo> findSkuInfoByKeyword(String keyword) {
        //1 根据keyword查询sku匹配内容列表
        //service-product模块创建接口 据关键字查询sku匹配内容列表
        //service-activity远程调用得到sku内容列表
        List<SkuInfo> skuInfoList = productFeignClient.findSkuInfoByKeyword(keyword);
        //判断：根据关键字查不到匹配内容，返回空集合
        if(skuInfoList.size()==0){
            return skuInfoList;
        }
        //从skuInfoList中获取所有skuId
        List<Long> skuIdList = skuInfoList.stream().map(SkuInfo::getId).collect(Collectors.toList());
        //2 判断添加的商品之前是否参加过活动（在活动期内），只能有一个活动
        //查询两张表判断 activity_info 和 activity_sku，编写SQL语句实现
        List<Long> existSkuIdList =  baseMapper.selectSkuIdListExist(skuIdList);
        // 判断逻辑处理，排除已参加活动商品
        List<SkuInfo> finalSkuList = new ArrayList<>();
        for (SkuInfo skuInfo:skuInfoList){
            if(!existSkuIdList.contains(skuInfo.getId())){
                finalSkuList.add(skuInfo);
            }
        }
        return finalSkuList;
    }
}
