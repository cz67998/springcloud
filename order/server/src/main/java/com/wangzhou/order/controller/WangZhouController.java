package com.wangzhou.order.controller;

import com.wangzhou.order.config.WangzhouConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/19
 * Time:11:02
 **/
@RestController
public class WangZhouController {
    @Autowired
    private WangzhouConfig wangzhouConfig;

    @GetMapping("/print")
    public String print() {
        return wangzhouConfig.getAge();
    }
}
