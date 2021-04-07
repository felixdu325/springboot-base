package com.dfz.dto;

/**
 * 用来返回json数据类型的结果
 * 
 * @param <T>
 */
public class AjaxResult<T> {
	
	/**
	 * 是否成功
	 */
	private Boolean success;

	/**
     * 状态码
     */
    private Integer code;
    
    /**
	 * 提示信息
	 */
	private String msg;
	
	/**
     * 服务器响应数据,返回的具体内容.
     * 附加对象，用来存储一些特定的返回信息
     */
    private T data;
    
    public AjaxResult() {
    }
    
	public AjaxResult(Boolean success) {
		super();
		this.success = success;
	}

	public AjaxResult(Integer code) {
		super();
		this.code = code;
	}

	
	public AjaxResult(String msg) {
		super();
		this.msg = msg;
	}


	
	public AjaxResult(Boolean success, Integer code) {
		super();
		this.success = success;
		this.code = code;
	}


	public AjaxResult(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	

	public AjaxResult(Boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}


	public AjaxResult(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public AjaxResult(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	
	public AjaxResult(Boolean success, Integer code, String msg) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
	}


	public AjaxResult(Boolean success, Integer code, String msg, T data) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}


	public Boolean getSuccess() {
		return success;
	}


	public void setSuccess(Boolean success) {
		this.success = success;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
	
	public static <T> AjaxResult<T> succeed() {
		return new AjaxResult<T>(true);
	}
	
	public static <T> AjaxResult<T>  succeed(Integer code) {
		return new AjaxResult<T>(true, code);
	}
	
	public static <T> AjaxResult<T>  succeed(String msg) {
		return new AjaxResult<T>(true, msg);
	}
	
	public static <T> AjaxResult<T> succeed(T data) {
		return new AjaxResult<T>(true, data);
	}
	
	public static <T> AjaxResult<T>  succeed(Integer code, String msg) {
		return new AjaxResult<T>(true, code, msg);
	}
	
	public static <T> AjaxResult<T>  succeed(Integer code, String msg, T data) {
		return new AjaxResult<T>(true, code, msg, data);
	}
	
	
	public static <T> AjaxResult<T> fail() {
		return new AjaxResult<T>(false);
	}
	
	public static <T> AjaxResult<T> fail(Integer code) {
		return new AjaxResult<T>(false, code);
	}
	
	public static <T> AjaxResult<T> fail(String msg) {
		return new AjaxResult<T>(false, msg);
	}
    
	public static <T> AjaxResult<T> fail(Integer code, String msg) {
		return new AjaxResult<T>(false, code, msg);
	}
    
}
