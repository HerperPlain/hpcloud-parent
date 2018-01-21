package com.hpsgts.hpcloud.ribbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-21
 * @packageName com.hpsgts.hpcloud.ribbon
 * @projectName hpcloud-parent
 * @company G翔时代技术服务有限公司
 */
@RestController
public class RibbonController {
    private static Logger logger = LoggerFactory.getLogger(RibbonController.class);
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/ribbon-say")
    public Object say(@RequestParam String name){
        logger.info("接收数据为：{}",name);
        return restTemplate.getForObject("http://hpcloud-eureka-client/say?name="+name,String.class);


    }

}
