package com.SnowSoft.springboot_web_camel.util;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponseDTO {
	private HttpStatus errorHttp;
	private String errorTitle;
	private int errorCode;
	private List<String> errorsList;
	
	public ErrorResponseDTO(HttpStatus errorHttp, String errorTitle, int errorCode, List<String> errorsList) {
		super();
		this.errorHttp = errorHttp;
		this.errorTitle = errorTitle;
		this.errorCode = errorCode;
		this.errorsList = errorsList;
	}
	
	public List<String> getErrorsList() {
		return errorsList;
	}

	public void setErrorsList(List<String> errorsList) {
		this.errorsList = errorsList;
	}

	public HttpStatus getErrorHttp() {
		return errorHttp;
	}
	public void setErrorHttp(HttpStatus errorHttp) {
		this.errorHttp = errorHttp;
	}
	public String getErrorTitle() {
		return errorTitle;
	}
	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
