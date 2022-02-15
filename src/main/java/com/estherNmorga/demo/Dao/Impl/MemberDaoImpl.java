package com.estherNmorga.demo.Dao.Impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.estherNmorga.demo.Dao.MemberDao;
import com.estherNmorga.demo.model.MemberModel;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcNameTemplate;
	
	@Override
	public Integer insert(MemberModel memberModel) {
		String sql = "INSERT INTO morga.esthermembership ("
				+ "		name, password, "
				+ "		phone, address, createDate, crypto"
				+ ")"
				+ "VALUE("
				+ "		:name, :password,"
				+ "		:phone, :address, NOW(), :crypto"
				+ ")";
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(memberModel);
		// 需要在id為 auto_increment下才可作為id, 並且反向回傳id值給前台或其他需求
		// 否則易一般情況下 ， 會顯示insert 失敗(少一項) / 又或者是回傳為 null (id 不是auto 並且被寫入其他資料)
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcNameTemplate.update(sql, paramSource, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public MemberModel findMemberByUserName(String username) {
		// TODO Auto-generated method stub
		String sql = " SELECT"
				+ "		id, name, password, phone, address, createDate, crypto"
				+ " FROM"
				+ "		morga.esthermembership"
				+ " WHERE"
				+ "		name = ?";
		
		List<MemberModel> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<MemberModel>(MemberModel.class), new Object[] {username});
		if(result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public Integer update(MemberModel memberModel) {
		// TODO Auto-generated method stub
		String sql = " UPDATE"
				+ " 	morga.esthermembership"
				+ "	SET"
				+ "		password = :password, createDate = NOW()"
				+ "	WHERE"
				+ "		id = :id";
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(memberModel);
		return jdbcNameTemplate.update(sql, paramSource);
	}

}
