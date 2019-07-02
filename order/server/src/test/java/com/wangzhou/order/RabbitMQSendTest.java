//package com.wangzhou.order;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.swing.*;
//
///**
// * Created by IDEA
// * author:wangzhou
// * Data:2018/10/19
// * Time:17:29
// **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class RabbitMQSendTest {
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//    @Test
//    public void rabbitMqSend() {
//        amqpTemplate.convertAndSend("myQueue","hello  rabbitmq");
//    }
//
//    @Test
//    public void rabbitMqOrder() {
//        amqpTemplate.convertAndSend("myOrder","computer","hello  rabbitmq");
//    }
//}
