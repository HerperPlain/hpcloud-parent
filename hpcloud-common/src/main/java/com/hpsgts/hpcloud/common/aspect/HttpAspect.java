package com.hpsgts.hpcloud.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 请求log日志记录
 * @author 黄朴（Herper.Plain）
 * @Date 2018/2/1 下午12:10
 */
@Aspect
@Component
public class HttpAspect {
	Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	
	/**pppp
	 * 定义切点为controller目录下的所有类的所有公共方法
	 */
	@Pointcut("execution(public * com.hpsgts.*.controller.*.*(..))")
	public void log() {
	}
	
	/**
	 * 进入方法前先打印下相关参数信息
	 * @param joinPoint
	 */
	@Before("log()")
	public void beforeLog(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// url
		logger.info("url={}", request.getRequestURL());
		// method
		logger.info("method={}", request.getMethod());
		// ip
		logger.info("ip={}", request.getRemoteAddr());
		// 类.方法
		logger.info("class.method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "()");
		// 请求参数
		logger.info("args={}", joinPoint.getArgs());
	}
	
	@After("log()")
	public void afterLog() {
		logger.info("after time={}", new Date());
	}
	
	@AfterReturning(returning = "object", pointcut = "log()")
	public void afterReturningLog(Object object) {
		// 判空处理
		logger.info("after returning={}", object == null ? "" : object.toString());
	}
}
