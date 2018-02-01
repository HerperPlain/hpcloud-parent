package com.hpsgts.hpcloud.common.exception;
/**
 * 
 * 自定义业务异常
 * @author conly.wang
 *
 */
public class BizException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4577935206598260993L;
	
	
	public BizException() {
		super();
	}

	public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

}
