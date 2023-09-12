/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.search.repository;

import com.atguigu.ssyx.model.search.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>
 * SkuRepository
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 8月 12, 2023
 */
public interface SkuRepository extends ElasticsearchRepository<SkuEs, Long> {
}
