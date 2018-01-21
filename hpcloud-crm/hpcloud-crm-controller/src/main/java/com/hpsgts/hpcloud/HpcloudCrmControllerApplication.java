package com.hpsgts.hpcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-20
 * @packageName com.hpsgts.hpcloud.crm.cntroller
 * @projectName crm-cntroller
 * @company G翔时代技术服务有限公司
 */
@SpringBootApplication
@Controller
public class HpcloudCrmControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HpcloudCrmControllerApplication.class, args);
	}
	@RequestMapping(value = "/")
	public String index(){
		return "redirect:index.html";
	}
}
