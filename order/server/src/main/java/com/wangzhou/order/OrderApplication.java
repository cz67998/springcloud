package com.wangzhou.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.wangzhou.product.client")
//@EnableCircuitBreaker
//一替三
@SpringCloudApplication
@ComponentScan(basePackages = "com.wangzhou")
@EnableHystrixDashboard

public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}
