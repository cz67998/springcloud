package com.wangzhou.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/21
 * Time:19:39
 **/
public interface StreamClient {
    // 接收消息、入口
    @Input("myMessageInput")
    SubscribableChannel input();

    // 发送消息、
    @Output("myMessageOutput")
    MessageChannel output();
}
