package com.estherNmorga.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estherNmorga.demo.Dao.RestDao;
import com.estherNmorga.demo.model.RestModel;
import com.estherNmorga.demo.service.RestService;

@Service
public class RestServiceImpl implements RestService{

	@Autowired
	private RestDao restDao;

	@Override
	public List<RestModel> findMemberByCompany(String company) {
		// TODO Auto-generated method stub
		return restDao.findMemberByCompany(company); 
	}

}
