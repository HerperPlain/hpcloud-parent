package com.hpsgts.hpcloud.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.hpsgts.hpcloud.common.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据库连接池
 * @author 黄朴（Herper.Plain）
 * @Date 2018/2/1 下午12:10
 * @Configuration
 */
public class DruidConfig {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.initialSize}")
	private int initialSize;

	@Value("${spring.datasource.minIdle}")
	private int minIdle;

	@Value("${spring.datasource.maxActive}")
	private int maxActive;

	@Value("${spring.datasource.maxWait}")
	private int maxWait;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.filters}")
	private String filters;
	@Value("${spring.datasource.druid.loginUsername}")
	private String loginUsername;
	@Value("${spring.datasource.druid.loginPassword}")
	private String loginPassword;
	@Value("${spring.datasource.druid.url}")
	private String url;
	
	@Value("${config.path}")
	private String configPath;
	@Value("${config.profile}")
	private String configProfile;

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),url);
        servletRegistrationBean.addInitParameter("loginUsername",loginUsername);
        servletRegistrationBean.addInitParameter("loginPassword",loginPassword);
        PropertiesUtil.setPath(configPath);
        PropertiesUtil.setProfile(configProfile);
        return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
		filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
		filterRegistrationBean.addInitParameter("resetEnable", "false");
		return filterRegistrationBean;
	}

	@Bean
	public DataSource druidDataSource() {
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(this.dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		return datasource;
	}

}