package com.wangzhou.order.message;

import com.wangzhou.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/21
 * Time:18:45
 **/
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {
    @StreamListener("myMessageOutput")
    @SendTo("myMessageInput")
    public OrderDTO process(OrderDTO message) {
        log.info("myMessageOutput : {}", message);

        return message;
    }

    @StreamListener("myMessageInput")
    public void success(OrderDTO message) {
        log.info("myMessageInput : {}", message);
    }
}
