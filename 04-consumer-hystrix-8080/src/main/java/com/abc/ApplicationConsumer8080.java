package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
//@EnableCircuitBreaker   开启熔断器
//@SpringBootApplication
@EnableHystrixDashboard  // 开启Dashboard功能
@EnableFeignClients  //开启Feign客户端
@SpringCloudApplication
public class ApplicationConsumer8080 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumer8080.class, args);
    }

}
