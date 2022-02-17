package com.estherNmorga.demo.Dao;

import java.util.List;

import com.estherNmorga.demo.model.RestModel;

public interface RestDao {

	public List<RestModel> findMemberByCompany(String company);

}
