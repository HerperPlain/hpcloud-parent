package com.hpsgts.hpcloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HpcloudConfigClientApplication {
	@Value("${foo}")
	String foo;
	@RequestMapping(value = "/foo")
	public String foo(){
		return foo;
	}
	public static void main(String[] args) {
		SpringApplication.run(HpcloudConfigClientApplication.class, args);
	}

}
