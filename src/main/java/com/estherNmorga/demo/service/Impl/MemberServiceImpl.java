package com.estherNmorga.demo.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.estherNmorga.demo.Dao.MemberDao;
import com.estherNmorga.demo.model.MemberModel;
import com.estherNmorga.demo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	private String getMd5Password(String password, String crypto) {
		// 對password + salt 進行三次加密
		String str = password + crypto;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}
	
	@Override
	public MemberModel login(MemberModel memberModel) {
		// TODO Auto-generated method stub
		
		// 檢查帳號存在與否
		MemberModel data = memberDao.findMemberByUserName(memberModel.getName());
		if(data == null)
		{
			return null;
		}
		
		// 登陸者密碼加密
		String cryptoPassword = this.getMd5Password(memberModel.getPassword(), data.getCrypto());
		
		// 比對密碼加密後正確與否
		if( !cryptoPassword.equals(data.getPassword()))
		{
			return null;
		}
		
		return data;
	}

	@Override
	public Optional<String> register(MemberModel memberModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberModel findMemberModelByName(String name) {
		// TODO Auto-generated method stub
		return memberDao.findMemberByUserName(name);
	}

}
