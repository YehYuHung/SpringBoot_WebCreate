package com.estherNmorga.demo.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherModel {
	private String success;
	private ResultModel result;
	private RecordsModel records;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ResultModel getResult() {
		return result;
	}

	public void setResult(ResultModel result) {
		this.result = result;
	}

	public RecordsModel getRecords() {
		return records;
	}

	public void setRecords(RecordsModel records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "WeatherModel {success=" + success + ", result=" + result + ", records=" + records + "}";
	}

	public WeatherModel(String success, ResultModel result, RecordsModel records) {
		this.success = success;
		this.result = result;
		this.records = records;
	}

	public WeatherModel() {
	}
}
