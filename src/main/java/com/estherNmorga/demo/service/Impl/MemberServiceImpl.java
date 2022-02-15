package com.estherNmorga.demo.service.Impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.estherNmorga.demo.Dao.MemberDao;
import com.estherNmorga.demo.model.MemberModel;
import com.estherNmorga.demo.service.MemberService;
import com.estherNmorga.demo.Library.MyLibrary;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MyLibrary myLibrary;
	
	@Autowired
	private MemberDao memberDao;
		
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
		String cryptoPassword = myLibrary.getMd5Password(memberModel.getPassword(), data.getCrypto());
		
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
		// 驗證欄位是否填寫正確格式
		if(!myLibrary.checkAddress(memberModel.getAddress())) 
		{
			return Optional.of("\u5730\u5740\u4e0d\u5305\u542b\u82f1\u6587\u5b57\u6bcd/\u5176\u4ed6\u7b26\u865f");
		}
		if(!myLibrary.checkPhone(memberModel.getPhone())) 
		{
			return Optional.of("\u8acb\u586b\u516510\u78bc\u4e26\u4e14\u662f\u884c\u52d5\u96fb\u8a71\u898f\u683c");
		}
		
		// 檢查帳號是否重複註冊
		MemberModel data = memberDao.findMemberByUserName(memberModel.getName());
		if(data != null)
		{
			return Optional.of("\u8a72\u5e33\u865f\u5df2\u88ab\u4f7f\u7528");
		}
		
		// 密碼進行加密動作
		String uuid = myLibrary.getCryptoUUID();
		String cryptoPassword = myLibrary.getMd5Password(memberModel.getPassword(), uuid);
		
		// 新增會員 Member 資料到資料庫
		MemberModel newMember = new MemberModel();
		newMember.setName(memberModel.getName());
		newMember.setPassword(cryptoPassword);
		newMember.setCrypto(uuid);
		newMember.setPhone(memberModel.getPhone());
		newMember.setAddress(memberModel.getAddress());
		Integer id = memberDao.insert(newMember);
		if(id == 0 || id == null)
		{
			return Optional.of("新增會員帳號時發生錯誤");
		}
		
		return Optional.empty();
	}

	@Override
	public MemberModel findMemberModelByName(String name) {
		// TODO Auto-generated method stub
		return memberDao.findMemberByUserName(name);
	}

}
