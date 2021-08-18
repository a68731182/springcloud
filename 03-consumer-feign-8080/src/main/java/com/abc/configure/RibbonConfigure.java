package com.abc.configure;

import com.abc.balance.InitRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

//负载均衡的策略修改配置类
@Controller
public class RibbonConfigure {


    @Bean
    public IRule loadBalanceRule() {
        List<Integer> excludePorts = new ArrayList<>();
        excludePorts.add(8083);
        return new InitRule(excludePorts);
    }
}
