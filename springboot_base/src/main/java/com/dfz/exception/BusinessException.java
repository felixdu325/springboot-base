package com.dfz.exception;

import java.io.Serializable;

/**
 * Custom Exception class
 *
 */
public class BusinessException extends RuntimeException implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer code=500;
	private String message;
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
	
	

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
	
	public BusinessException(Integer code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.message = message;
	}
	
	public BusinessException(String message) {
		super(message);
		this.message = message;
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
	}

	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
