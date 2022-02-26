package com.estherNmorga.demo.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeModel {
	private String startTime;
	private String endTime;
	private ParameterModel parameter;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public ParameterModel getParameter() {
		return parameter;
	}

	public void setParameter(ParameterModel parameter) {
		this.parameter = parameter;
	}

	@Override
	public String toString() {
		return "{\"startTime\":\"" + startTime + "\", \"endTime\":\"" + endTime + "\", \"parameter\":\"" + parameter
				+ "\"}";
	}

	public TimeModel(String startTime, String endTime, ParameterModel parameter) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.parameter = parameter;
	}

	public TimeModel() {
	}
}
