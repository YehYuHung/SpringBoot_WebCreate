package com.estherNmorga.demo.model.weather;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationsModel {
	private String locationName;
	private ArrayList<WeatherElementModel> weatherElement;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public ArrayList<WeatherElementModel> getWeatherElement() {
		return weatherElement;
	}

	public void setWeatherElement(ArrayList<WeatherElementModel> weatherElement) {
		this.weatherElement = weatherElement;
	}

	@Override
	public String toString() {
		return "{\"locationName\":\"" + locationName + "\", \"weatherElement\":\"" + weatherElement + "\"}";
	}

	public LocationsModel(String locationName, ArrayList<WeatherElementModel> weatherElement) {
		this.locationName = locationName;
		this.weatherElement = weatherElement;
	}

	public LocationsModel() {
	}
}
