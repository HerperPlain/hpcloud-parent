package com.hpsgts.hpcloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-20
 * @packageName com.hpsgts.ribbon
 * @projectName hpcloud-parent
 * @company G翔时代技术服务有限公司
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HpcloudRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HpcloudRibbonApplication.class, args);
    }
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
