package com.estherNmorga.demo.service;

import java.util.Optional;

import com.estherNmorga.demo.model.MemberModel;

public interface MemberService {

	// 業務邏輯
	public MemberModel login(MemberModel memberModel);
	public void register(MemberModel memberModel);
	
	// 資料庫操作
	public MemberModel findMemberModelByName(String name);
	
}
