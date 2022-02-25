package com.estherNmorga.demo.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldModel {
	private String id;
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FieldModel {id=" + id + ", type=" + type + "}";
	}

	public FieldModel(String id, String type) {
		this.id = id;
		this.type = type;
	}

	public FieldModel() {
	}
}
