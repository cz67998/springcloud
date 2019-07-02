package com.wangzhou.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/18
 * Time:17:22
 **/
@RestController
@RequestMapping("/env")
@RefreshScope//刷新git下配置的属性
public class EnvController {
    @Value("${env}")
    private String env;
//    @Value("${wangzhou}")
//    private String name;

    @GetMapping("/print")
    public String print() {
        return env;
    }

//    @GetMapping("/print1")
//    public String print1() {
//        return name;
//    }
}
