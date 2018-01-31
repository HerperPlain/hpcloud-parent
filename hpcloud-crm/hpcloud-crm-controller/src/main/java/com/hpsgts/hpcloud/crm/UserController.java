package com.hpsgts.hpcloud.crm;

import com.hpsgts.hpcloud.common.controller.BaseController;
import com.hpsgts.hpcloud.crm.services.base.BaseService;
import com.hpsgts.hpcloud.model.crm.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-21
 * @packageName com.hpsgts.hpcloud.crm
 * @projectName hpcloud-parent
 * @company G翔时代技术服务有限公司
 */
@RestController
public class UserController extends BaseController{
    @Autowired
    BaseService<SysUserEntity> baseService;

    @RequestMapping("/hello")
    public String hello(){
        return "1111111";
    }
}
