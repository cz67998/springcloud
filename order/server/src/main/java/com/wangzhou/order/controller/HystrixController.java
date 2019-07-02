package com.wangzhou.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/30
 * Time:18:46
 **/
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
    //fallback是一个方法名
    //@HystrixCommand(fallbackMethod = "fallback")

    /**
     * 超时配置
     * @HystrixCommand(commandProperties ={
    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    } )
     */
  @HystrixCommand(commandProperties = {
          @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//设置熔断
          @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
          @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
          //10000ms是休眠时间，到期后释放一次请求，成功就关闭，失败就再次开启，休眠时间窗重新计时
          @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")
  })
//    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number) {
      if(number%2==0){
          return  "success";
      }
        //新创建的RestTemplate对象不是  @Autowired的，没有这个属性 @LoadBalanced
        RestTemplate restTemplate = new RestTemplate();
        //第三个参数是返回的类型
        return restTemplate.postForObject("http://127.0.0.1:8082/product/listForOrder",
                Arrays.asList("1"),
                String.class);
//        throw new RuntimeException("异常");
    }
    //上面出问题了，就会跳到下面的方法//实现服务降级
    // 只要有异常 //响应时间过1秒  默认1s
    private String fallback(){
        return "太拥挤了，请稍后再试~~";
    }
    private String defaultFallback(){
        return "默认提示：太拥挤了，请稍后再试~~";
    }
}
