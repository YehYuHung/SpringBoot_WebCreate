package com.estherNmorga.demo.model;

public class OutputInfo {
	private String message;
	private Base data;
	private String status;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Base getData() {
		return data;
	}
	public void setData(Base data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OutputInfo(String message, Base data, String status) {
		super();
		this.message = message;
		this.data = data;
		this.status = status;
	}
}
