package com.estherNmorga.demo.model.weather;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordsModel {
	private String datasetDescription;
	private ArrayList<LocationsModel> location;

	public String getDatasetDescription() {
		return datasetDescription;
	}

	public void setDatasetDescription(String datasetDescription) {
		this.datasetDescription = datasetDescription;
	}

	public ArrayList<LocationsModel> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<LocationsModel> location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "{\"datasetDescription\":\"" + datasetDescription + "\", \"location\":\"" + location + "\"}";
	}

	public RecordsModel(String datasetDescription, ArrayList<LocationsModel> location) {
		this.datasetDescription = datasetDescription;
		this.location = location;
	}

	public RecordsModel() {
	}
}
