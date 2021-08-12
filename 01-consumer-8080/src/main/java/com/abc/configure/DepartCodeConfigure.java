package com.abc.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class DepartCodeConfigure {
    @Bean  //RestTemplate spring 3.0开始提供的一种支持的一个 HTTP  请求工具
    public RestTemplate restTemplate(){
       return new RestTemplate();
   }
}
