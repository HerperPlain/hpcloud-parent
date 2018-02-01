package com.hpsgts.hpcloud.common.enums;

/**
 * @author 黄朴（Herper.Plain）
 * @Date 2018/1/30 下午2:15
 * @Company 青朴信息技术服务有限公司
 */
public enum  ResponseEnum {
    SUCCESS(2000,"成功"),
    FAIL_SERVER(5000,"业务服务数据请求不合法!"),
    ERROR_PARAMS(5010,"请求参数不合法！"),
    BAD_REQUEST(4010,"请求不合法！"),
    ERROR_RUNTIME(5000,"业务服务器处理错误!"),
    NOT_FOUND(4040,"资源或文件不存在"),
    NO_RESPONSE(0000,"徕卡接口没有返回"),
    ERROR(99999,"系统内部错误"),
    REPEATED_ERROR(5022,"重复申请"),
    OVERRUN_ERROR(5033,"超出限制");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
