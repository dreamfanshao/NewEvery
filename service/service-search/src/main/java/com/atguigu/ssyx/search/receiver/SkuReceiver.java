/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.search.receiver;

import com.atguigu.ssyx.mq.constant.MqConst;
import com.atguigu.ssyx.search.service.SkuService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 * SkuReceiver
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 8月 14, 2023
 */
@Component
public class SkuReceiver {

    @Autowired
    private SkuService skuService;

    //上架
    @RabbitListener(bindings = @QueueBinding(
                    value = @Queue(value = MqConst.QUEUE_GOODS_UPPER,durable = "true"),
                    exchange = @Exchange(value = MqConst.EXCHANGE_GOODS_DIRECT),
                    key = {MqConst.ROUTING_GOODS_UPPER}))
    public void upperSku(Long skuId, Message message, Channel channel) throws IOException {
        if (null != skuId) {
            skuService.upperSku(skuId);
        }
        /**
         * 手动确认
         * 第一个参数：表示收到的消息的标号
         * 第二个参数：如果为true表示可以签收多个消息
         */
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    //下架
    @RabbitListener(bindings = @QueueBinding(
                    value = @Queue(value = MqConst.QUEUE_GOODS_LOWER, durable = "true"),
                    exchange = @Exchange(value = MqConst.EXCHANGE_GOODS_DIRECT),
                    key = {MqConst.ROUTING_GOODS_LOWER}))
    public void lowerSku(Long skuId, Message message, Channel channel) throws IOException {
        if (skuId != null) {
            skuService.lowerSku(skuId);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
