package com.hpsgts.hpcloud.common.base;

import java.util.List;

/**
 * @author 黄朴（Hp.Plain)
 * @date 2018-1-21
 * @packageName com.hpsgts.hpcloud.common.base
 * @projectName hpcloud-parent
 * @company G翔时代技术服务有限公司
 */
public interface BaseService<T> {
    int save(T entity);

    int delete(T entity);

    int update(T entity);

    List<T> query(T entity);
}
