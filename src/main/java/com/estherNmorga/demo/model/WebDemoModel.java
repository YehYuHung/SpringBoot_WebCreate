package com.estherNmorga.demo.model;

public class WebDemoModel extends Base {
	
	private long id;
	private String content;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public WebDemoModel(long id, String content) {
		this.id = id;
		this.content = content;
	}
}
