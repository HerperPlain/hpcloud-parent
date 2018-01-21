package com.hpsgts.eureka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
/**
 * 标注此为一个注册中心
 */
@EnableEurekaClient
@RestController
/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-20
 * @packageName com.hpsgts.eureka.client
 * @projectName eureka-client
 * @company G翔时代技术服务有限公司
 */
public class EurekaClientApplication {

	/**
	 * 初始话载入application.properties中的数据
	 */
	@Value("${server.port}")
	String port;

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}



	@RequestMapping("/say")
	public String say(@RequestParam String name){
		return name +" Say hello world !!! server-port:"+port;
	}
}
