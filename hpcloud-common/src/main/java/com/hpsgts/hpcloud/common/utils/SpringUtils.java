package com.hpsgts.hpcloud.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author 黄朴（Herper.Plain）
 * @Date 2018/02/01 下午12:30
 */
public class SpringUtils implements BeanFactoryPostProcessor {
    @Autowired
    private static ConfigurableListableBeanFactory beanFactory;
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    public static  <T> T getBean(String name){
        return (T) beanFactory.getBean(name);
    }
    public static <T> T getBean(Class<T> tClass){
        return beanFactory.getBean(tClass);
    }

}
