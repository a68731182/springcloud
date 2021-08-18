package com.abc.configure;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class DepartCodeConfigure {
    @LoadBalanced  //负载均衡
    @Bean  //RestTemplate spring 3.0开始提供的一种支持的一个 HTTP  请求工具
    public RestTemplate restTemplate(){
       return new RestTemplate();
   }
}
