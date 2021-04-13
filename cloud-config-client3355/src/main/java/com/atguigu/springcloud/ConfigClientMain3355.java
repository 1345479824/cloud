package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 访问地址 localhost:3355/configInfo
 *
 * 当github配置文件修改了之后，要在controller加个注解配置、在bootstrap加配置
 * 最后还需要在cmd窗口执行命令curl -X POST "http://localhost:3355/actuator/refresh"
 * 3355微服务才能够感知github配置文件的变化
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
