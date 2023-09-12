/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.mq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * RabbitService
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 8月 13, 2023
 */
@Service
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送消息的方法
    //交换机exchange,路由routingKey,消息message
    public boolean sendMessage(String exchange,String routingKey,Object message){
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
        return true;
    }
}
