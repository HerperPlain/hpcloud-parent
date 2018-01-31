package com.hpsgts.hpcloud.common.controller;

import com.hpsgts.hpcloud.common.enums.ResponseEnum;
import com.hpsgts.hpcloud.common.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

/**
 * @author 黄朴（Herper.Plain）
 * @Date 2018/1/30 下午2:14
 * @Company 上海宏鹿信息技术服务有限公司
 */
@ControllerAdvice
public class BaseController {
    Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 为参数验证添加异常处理器
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public Response handleConstraintViolationException(ConstraintViolationException exception){
        //这里简化处理了，getConstraintViolations 会得到所有错误信息的迭代，可以酌情处理
        String errorMessage = exception.getConstraintViolations().iterator().next().getMessage();
        logger.info("错误异常：{}",errorMessage);
        logger.error(exception.getMessage(),exception);
        return new Response(ResponseEnum.ERROR_PARAMS);
    }
}
