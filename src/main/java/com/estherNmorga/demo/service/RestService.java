package com.estherNmorga.demo.service;

import java.util.List;

import com.estherNmorga.demo.model.RestModel;

public interface RestService {

	// 資料庫操作
	public List<RestModel> findMemberByCompany(String company);

}
