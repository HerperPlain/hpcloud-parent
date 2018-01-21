package com.hpsgts.hpcloud.model.crm.dao;

import com.hpsgts.hpcloud.model.crm.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-21
 * @packageName com.hpsgts.hpcloud.model.crm.dao
 * @projectName hpcloud-parent
 * @company G翔时代技术服务有限公司
 */
@Repository
public interface SysUserEntityDao extends JpaRepository<SysUserEntity,String> {
}
