package com.estherNmorga.demo;

import java.util.Optional;
import java.util.UUID;

import javax.websocket.Decoder.Binary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.estherNmorga.demo.Dao.MemberDao;
import com.estherNmorga.demo.model.MemberModel;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 寫入資料測試 Dao.insert()
	 * @param name <會員名稱>
	 * @param password <會員密碼>
	 * @param phone <會員手機>
	 * @param address <會員地址>
	 */
	public void insert() {
		MemberModel memberModel = new MemberModel();
		memberModel.setName("morga.Yeh");
		memberModel.setPassword("onlylove317920");
		memberModel.setPhone("0987930607");
		memberModel.setAddress("新北市永和區");
		Integer id = memberDao.insert(memberModel);
		System.out.println("Inert Success! id :" + id);
	}
	
	/**
	 * 找尋資料測試 Dao.findMemberByUserName()
	 * @param name <會員的名稱>
	 */
	public void findMemberByUserName() {
		String name = "morga.Yeh";
		MemberModel memberModel = memberDao.findMemberByUserName(name);
		System.out.println(memberModel != null? memberModel.toString(): "I'm null sorry.");
	}

	/**
	 * 更新密碼測試 Dao.update() 
	 * @param id <該會員的編號>
	 * @param password <更新的密碼>
	 */
	public void update() {
		MemberModel memberModel = new MemberModel();
		memberModel.setId(1);
		memberModel.setPassword("Qwerty12345");
		Integer result = memberDao.update(memberModel);
		System.out.println("success! result : " + result);
	}
}
