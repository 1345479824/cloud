package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties //设置默认的运行时异常、超时异常
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String res = paymentHystrixService.paymentInfo_OK(id);
        return res;
    }
    //服务降级
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand (fallbackMethod = "paymentTimeOutFallBackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    }) //设置的运行时异常、超时异常的兜底方案为paymentTimeOutFallBackMethod
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        //int a = 10 / 0;
        String res = paymentHystrixService.paymentInfo_timeout(id);
        return res;
    }
    public String paymentTimeOutFallBackMethod(@PathVariable("id") Integer id){
        return "对方支付系统繁忙，请稍后再试,o(╥﹏╥)o";
    }


}
