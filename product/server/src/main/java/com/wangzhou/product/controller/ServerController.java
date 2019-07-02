package com.wangzhou.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/14
 * Time:13:05
 **/
@RestController
public class ServerController {
    @GetMapping("msg")
    public String msg(){
      return "This is product' msg 2";
    }
}
