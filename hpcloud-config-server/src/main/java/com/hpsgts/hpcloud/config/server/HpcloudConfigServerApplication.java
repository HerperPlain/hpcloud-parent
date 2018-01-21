package com.hpsgts.hpcloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-20
 * @packageName com.hpsgts.eureka.client
 * @projectName eureka-client
 * @company G翔时代技术服务有限公司
 */
@SpringBootApplication
@EnableConfigServer
public class HpcloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HpcloudConfigServerApplication.class, args);
	}
}
