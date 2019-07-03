package com.infy.cpc.fas.response;

public class ResponseStatus {
	private String status;
	private int code;
	private String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStatCode() {
		return code;
	}
	public void setStatCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}