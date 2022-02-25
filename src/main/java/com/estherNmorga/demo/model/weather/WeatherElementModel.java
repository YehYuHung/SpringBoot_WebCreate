package com.estherNmorga.demo.model.weather;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherElementModel {
	private String elementName;
	private ArrayList<TimeModel> time;

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public ArrayList<TimeModel> getTime() {
		return time;
	}

	public void setTime(ArrayList<TimeModel> time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "WeatherElementModel {elementName=" + elementName + ", time=" + time + "}";
	}

	public WeatherElementModel(String elementName, ArrayList<TimeModel> time) {
		this.elementName = elementName;
		this.time = time;
	}

	public WeatherElementModel() {
	}
}
