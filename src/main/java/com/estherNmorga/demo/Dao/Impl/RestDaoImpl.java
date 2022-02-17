package com.estherNmorga.demo.Dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.estherNmorga.demo.Dao.RestDao;
import com.estherNmorga.demo.model.RestModel;

@Repository
public class RestDaoImpl implements RestDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcNameTemplate;

	
	@Override
	public List<RestModel> findMemberByCompany(String company) {
		// TODO Auto-generated method stub
		String sql = " SELECT"
				+ "		id, first_name, last_name, email, company, gender"
				+ " FROM"
				+ "		morga.restfulapi"
				+ " WHERE"
				+ "		company = ?";
		
		List<RestModel> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RestModel>(RestModel.class), new Object[] {company});
		if(result != null && result.size() > 0) {
			return result;
		}
		return null;
	}

}
