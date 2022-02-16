package com.estherNmorga.demo.Library;

import java.util.Iterator;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class MyLibrary {
	public String getMd5Password(String password, String crypto) {
		// 對password + salt 進行三次加密
		String str = password + crypto;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}
	
	public String getCryptoUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
	
	public boolean checkPhone(String phone) {
		// 空值
		if( phone.isEmpty() )
		{
			return false;
		}
		// 電話長度 10 - 13
		if(phone.length()<10 || phone.length()>13)
		{
			return false;
		}
		
		// 檢查號碼不含英文字母或其他符號
		for (int i = 0; i < phone.length(); i++) {
			if(!Character.isDigit(phone.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	// 未來串接地址api做驗證 ， 目前只判斷不包含英文字母
	public boolean checkAddress(String address) {
		// 空值
		if( address.isEmpty() )
		{
			return false;
		}
		
		// 檢查號碼不含英文字母或其他符號
		for (int i = 0; i < address.length(); i++) {
			if( !this.isChinese(address.charAt(i)) && !Character.isDigit(address.charAt(i)) ) {
				return false;
			}
		}
		
		return true;
	}
	
	// 檢測中文
	// url : https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/323197/
    private boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
}
