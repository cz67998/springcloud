package com.wangzhou.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收mq的消息
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/19
 * Time:17:23
 **/
@Slf4j
@Component
public class RabbitMQReceiver {
    //1.手动创建队列@RabbitListener(queues = "myQueue")
    //2.自动创建队列
    //@RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3.自动创建，Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("RabbitMQReceiver:{}"+message);
    }

    /**
     * 电脑供应商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("computerOrder"),
            key = "computer",
            exchange = @Exchange("myOrder")
    ))
    public void processcomputer(String message) {
        log.info("RabbitMQReceiver:{}"+message);
    }

    /**
     * 水果供应商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruitOrder"),
            key = "fruit",
            exchange = @Exchange("myOrder")
    ))
    public void processfruit(String message) {
        log.info("RabbitMQReceiver:{}"+message);
    }
}
