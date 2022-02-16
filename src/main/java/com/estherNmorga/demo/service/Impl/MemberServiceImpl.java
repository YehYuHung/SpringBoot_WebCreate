package com.estherNmorga.demo.service.Impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.estherNmorga.demo.Dao.MemberDao;
import com.estherNmorga.demo.model.MemberModel;
import com.estherNmorga.demo.service.MemberService;
import com.estherNmorga.demo.service.exception.InsertException;
import com.estherNmorga.demo.service.exception.MemberNotFoundException;
import com.estherNmorga.demo.service.exception.PasswordNotCorrectException;
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
			throw new MemberNotFoundException("帳號或密碼錯誤");
		}
		
		// 登陸者密碼加密
		String cryptoPassword = myLibrary.getMd5Password(memberModel.getPassword(), data.getCrypto());
		
		// 比對密碼加密後正確與否
		if( !cryptoPassword.equals(data.getPassword()))
		{
			throw new PasswordNotCorrectException("帳號或密碼錯誤");
		}

		return data;
	}

	@Override
	public void register(MemberModel memberModel) {
		// TODO Auto-generated method stub
		// 驗證欄位是否填寫正確格式
		if(!myLibrary.checkPhone(memberModel.getPhone())) 
		{
			throw new MemberNotFoundException("請填入10碼並且是行動電話規格");
		}
		if(!myLibrary.checkAddress(memberModel.getAddress())) 
		{
			throw new MemberNotFoundException("地址不包含英文字母/其他符號");
		}
		if(memberModel.getName().isEmpty() || memberModel.getPassword().isEmpty())
		{
			throw new MemberNotFoundException("請輸入帳號及密碼");
		}
		
		// 檢查帳號是否重複註冊
		MemberModel data = memberDao.findMemberByUserName(memberModel.getName());
		if(data != null)
		{
			throw new MemberNotFoundException("該帳號已被使用");
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
			throw new InsertException("新增會員帳號時發生錯誤");
		}
	}

	@Override
	public MemberModel findMemberModelByName(String name) {
		// TODO Auto-generated method stub
		return memberDao.findMemberByUserName(name);
	}

}
