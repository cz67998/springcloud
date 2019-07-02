//package com.wangzhou.order.message;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.wangzhou.order.utils.JsonUtil;
//import com.wangzhou.product.ProductInfoOutput;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * Created by IDEA
// * author:wangzhou
// * Data:2018/10/22
// * Time:11:40
// **/
//@Component
//@Slf4j
//public class ProductInfoReceiver {
//    public static final String PRODUCT_STOCK_TEMPLATE="product_stock_%s";
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
//    public void process(String message){
//        System.out.println(message);
//        List<ProductInfoOutput> productInfoOutputList=(List<ProductInfoOutput>)JsonUtil.fromJsonList(message,
//                new TypeReference<List<ProductInfoOutput>>() {});
//        log.info("RabbitMQReceiver:{}"+productInfoOutputList);
//        for (ProductInfoOutput productInfoOutput:productInfoOutputList) {
//            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,productInfoOutput.getProductId()),productInfoOutput.getProductStock().toString());
//
//        }
//        //储存到redis里面
//       }
//}
