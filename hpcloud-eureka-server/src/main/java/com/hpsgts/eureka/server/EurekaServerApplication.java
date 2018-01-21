package com.hpsgts.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-20
 * @packageName com.hpsgts.eureka.server
 * @projectName hpcloud-parent
 * @company G翔时代技术服务有限公司
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args){
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
