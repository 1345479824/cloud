package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort; //集群的时候可以用到，获取集群中实际提供服务的那台机器的端口号

    @Resource
    private DiscoveryClient discoveryClient; //通过服务发现来获取该服务在eureka里的信息

    @PostMapping(value = "/payment/create")
    //requestbody一定要加，因为80端口传过来的是一个对象，要把它转化为jason字符串才行
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果: " + result);
        if (result > 0){
            return new CommonResult(200, "插入数据库成功,serverport:" + serverPort, result);
        }else{
            return new CommonResult(444, "插入数据库失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****插入结果: " + payment);
        if (payment != null){
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        }else{
            return new CommonResult(444, "没有对应记录，查询ID："+id);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();//获取服务列表信息
        for (String element:services){
            log.info("element: " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances){
            log.info(instance.getServiceId() + " " + instance.getHost() + " " + instance.getPort() + " " + instance.getUri());
        }
        return discoveryClient;
    }

}
