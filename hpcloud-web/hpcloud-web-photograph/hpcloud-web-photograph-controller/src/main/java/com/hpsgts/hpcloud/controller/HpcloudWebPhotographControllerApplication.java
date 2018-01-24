package com.hpsgts.hpcloud.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-23
 * @packageName com.hpsgts.hpcloud.
 * @projectName Photograph
 * @company G翔时代技术服务有限公司
 */
@SpringBootApplication
@Controller
public class HpcloudWebPhotographControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HpcloudWebPhotographControllerApplication.class, args);
	}
	@RequestMapping("/")
	public String index(){
		return "redirect:index.html";
	}
}
