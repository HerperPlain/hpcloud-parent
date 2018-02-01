package com.hpsgts.hpcloud.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取config.properties配置
 * @author 黄朴（Herper.Plain）
 * @Date 2018/02/01 下午12:30
 */
public class PropertiesUtil {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	private static Properties props = new Properties() ;
	
	private static String path ;
	
	private static String profile  ;

	public static void loadAllProperties(){
        try {
        	if (Detect.equal(profile, "local")) {
        		Resource resource = new ClassPathResource(path) ;
        		props = PropertiesLoaderUtils.loadProperties(resource) ;
        	} else {
        		logger.info("读取配置信息path ：{}",path);
        		InputStream in = new FileInputStream(new File (path)) ;
        		props.load(in);
        		logger.info("读取配置信息：{}",props);
        	}
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("加载config 配置失败 ！");
        }
    }

	public static String get(String key , boolean isReload) {
		
		if (isReload || !Detect.notEmpty(props)) {
			loadAllProperties();
		}
		return props == null ? null : props.getProperty(key);

	}
	
	public static String get(String key ) {
		
		if (!Detect.notEmpty(props)) {
			loadAllProperties();
		}
		return props == null ? null : props.getProperty(key);

	}
	public static Properties getProperties() {
		return props;
	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		PropertiesUtil.path = path;
	}

	public static Properties getProps() {
		return props;
	}

	public static void setProps(Properties props) {
		PropertiesUtil.props = props;
	}

	public static String getProfile() {
		return profile;
	}

	public static void setProfile(String profile) {
		PropertiesUtil.profile = profile;
	}
	
	
}
