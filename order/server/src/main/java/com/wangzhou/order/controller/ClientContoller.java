package com.wangzhou.order.controller;



import com.wangzhou.product.DecreaseStockInput;
import com.wangzhou.product.ProductInfoOutput;
import com.wangzhou.product.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/14
 * Time:13:10
 **/
@RestController
@Slf4j
public class ClientContoller {
    public static void main(String[] args) {
        String url = "http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo";
        String json = loadJSON(url);
        System.out.println(json);
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //1.RestTemplate的第一种方法（直接使用restTemplate）
        //缺点：url写死了，不适用于多个地址的使用
        //  RestTemplate restTemplate=new RestTemplate();
//    String reponse= restTemplate.getForObject("http://127.0.0.1:8080/msg",String.class);
//    log.info("reponse={}",reponse);

        //2.第二种方式//在注册eureka上注册的用户名(利用loadBalancerClient)
//       ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        serviceInstance.getHost();
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/msg");
//        RestTemplate restTemplate = new RestTemplate();
//        String reponse = restTemplate.getForObject(url, String.class);

        //3.第三种方式(在RestTemplateConfig类里使用 @LoadBalanced，可在restTemplate里使用应用名字)
        //@LoadBalanced必须使用应用名字，不能使用127.0.0.1：
        // RestTemplate restTemplate=new RestTemplate();
        String reponse = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        log.info("reponse={}", reponse);
        return reponse;
    }

    @Autowired
    private ProductClient productClient;




    @GetMapping("/getProductList")
    public  String ProductList(){
    List<ProductInfoOutput> productInfoList=productClient.listForOrder(Arrays.asList("1","2"));
    log.info("reponse={}",productInfoList);
    return "ok";
    }
    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        DecreaseStockInput cartDTO=new DecreaseStockInput("1",1);
        productClient.decreaseStock(Arrays.asList(cartDTO));
        return "ok";
    }
}
