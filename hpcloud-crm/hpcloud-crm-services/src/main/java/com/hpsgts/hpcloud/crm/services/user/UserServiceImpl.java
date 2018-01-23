package com.hpsgts.hpcloud.crm.services.user;


import com.hpsgts.hpcloud.crm.services.base.BaseService;
import com.hpsgts.hpcloud.model.crm.dao.SysUserEntityDao;
import com.hpsgts.hpcloud.model.crm.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-21
 * @packageName com.hpsgts.hpcloud.crm.services.user
 * @projectName hpcloud-parent
 * @company G翔时代技术服务有限公司
 */
@Component
public class UserServiceImpl implements BaseService<SysUserEntity> {
    @Autowired
    SysUserEntityDao baseDao;
    @Override
    public int save(SysUserEntity entity) {
        return 0;
    }

    @Override
    public int delete(SysUserEntity entity) {
        return 0;
    }

    @Override
    public int update(SysUserEntity entity) {
        return 0;
    }

    @Override
    public List<SysUserEntity> query(SysUserEntity entity) {
        return null;
    }
}
