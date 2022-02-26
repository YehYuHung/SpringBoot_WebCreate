package com.estherNmorga.demo.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterModel {
	private String parameterName;
	private String parameterUnit;

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterUnit() {
		return parameterUnit;
	}

	public void setParameterUnit(String parameterUnit) {
		this.parameterUnit = parameterUnit;
	}

	@Override
	public String toString() {
		return "{\"parameterName\":\"" + parameterName + "\", \"parameterUnit\":\"" + parameterUnit + "\"}";
	}

	public ParameterModel(String parameterName, String parameterUnit) {
		this.parameterName = parameterName;
		this.parameterUnit = parameterUnit;
	}

	public ParameterModel() {
		// TODO Auto-generated constructor stub
	}
}
