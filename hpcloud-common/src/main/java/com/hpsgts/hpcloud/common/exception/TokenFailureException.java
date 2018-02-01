package com.hpsgts.hpcloud.common.exception;

/**
 * token失效异常类
 * @author 黄朴（Herper.Plain）
 * @Date 2018/02/01 下午12:30
 */
public class TokenFailureException extends RuntimeException {

    private static final long serialVersionUID = -3023064447387224239L;

    private String code;

    public TokenFailureException() {
        super();
    }

    public TokenFailureException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public TokenFailureException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    public TokenFailureException(String message) {
        super(message);
    }

    public TokenFailureException(Throwable cause) {
        super(cause);
    }

    public TokenFailureException(String messeage, Throwable cause) {
        super(messeage, cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
