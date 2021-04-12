package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ribbon负载均衡规则定义类
 *
 * ribbon有以下的规则：
 * ①RoundRobinRule 轮询
 * ②RandomRule 随机
 * ③RetryRule
 * ④WeightedResponseTimeRule
 * ⑤BestAvailableRule
 * ⑥AvailabilityFilteringRule
 * ⑦ZoneAvoidanceRule
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();  //定义为随机
    }
}
