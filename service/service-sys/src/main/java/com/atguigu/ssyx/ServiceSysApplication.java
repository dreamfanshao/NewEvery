/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>
 * ServiceSysApplication
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 7月 06, 2023
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSysApplication.class, args);
    }
}
