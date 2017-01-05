package com.squad.cccreview.model;

public class BaseResponse {

	public BaseResponse(boolean b, String string) {
		this.success=b;
		this.message=string;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	boolean success;
	String message;
}
