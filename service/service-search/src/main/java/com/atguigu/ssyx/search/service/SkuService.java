/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.search.service;

/**
 * <p>
 * SkuService
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 8月 12, 2023
 */
public interface SkuService {
    void upperSku(Long skuId);

    void lowerSku(Long skuId);
}
