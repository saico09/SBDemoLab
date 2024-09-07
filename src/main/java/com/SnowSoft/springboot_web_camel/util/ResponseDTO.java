package com.SnowSoft.springboot_web_camel.util;

public class ResponseDTO {
	private String status;
	private Object data;
	public ResponseDTO(String status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
