package com.estherNmorga.demo.model.weather;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultModel {
	private String resource_id;
	private ArrayList<FieldModel> fields;

	public String getResource_id() {
		return resource_id;
	}

	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}

	public ArrayList<FieldModel> getFields() {
		return fields;
	}

	public void setFields(ArrayList<FieldModel> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "ResultModel {resource_id=" + resource_id + ", fields=" + fields + "}";
	}

	public ResultModel(String resource_id, ArrayList<FieldModel> fields) {
		this.resource_id = resource_id;
		this.fields = fields;
	}

	public ResultModel() {
	}
}
