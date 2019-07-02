package com.wangzhou.order.controller;


import com.wangzhou.order.dto.OrderDTO;
import com.wangzhou.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/21
 * Time:18:49
 **/
@RestController
public class SendMessageController {
    @Autowired
    private StreamClient streamClient;

    /**
     * stream=传递字符串
     */
    @GetMapping("/sendMessage")
    public void process(){
        streamClient.output().send(MessageBuilder.withPayload("now"+new Date()).build());
    }


    /**
     * 发送OrderDTO对象
     */
    @GetMapping("/sendMessageObject")
    public void sendMessageObject(){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
