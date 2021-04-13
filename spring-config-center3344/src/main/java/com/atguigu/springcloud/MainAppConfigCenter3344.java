package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
    配置中心
    访问地址 http://localhost:3344/main/config-dev.yml

    配置了rabbitmq总线访问，可以进行广播，具体如下：
    1、修改了github的配置文件
    2、cmd窗口执行curl -X POST "http://localhost:3344/actuator/bus-refresh"命令
    （若只想广播到3355，则在命令后加/config-client:3355即可）
    3、执行了2的命令之后，该修改就会通过3344配置中心广播到3344上的3355微服务和3366微服务
       从而3355和3366微服务都知道配置文件进行了修改。若不执行2命令，那么3355和3366将无法得知
       github配置文件的修改。

 */
@SpringBootApplication
@EnableConfigServer
public class MainAppConfigCenter3344 {
    public static void main(String[] args) {
        SpringApplication.run(MainAppConfigCenter3344.class, args);
    }
}
