package com.estherNmorga.demo.Dao;

import com.estherNmorga.demo.model.MemberModel;

public interface MemberDao {
	
	public Integer insert(MemberModel memberModel);
	public MemberModel findMemberByUserName(String username);
	public Integer update(MemberModel memberModel);
	
}
