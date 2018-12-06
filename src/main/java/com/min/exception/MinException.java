package com.min.exception;

/**
 * 自定义异常
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年10月27日 下午10:11:27
 */
public class MinException extends RuntimeException {
	
	private String msg;
	private int code = 500;
	
	public MinException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public MinException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public MinException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public MinException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	
}
