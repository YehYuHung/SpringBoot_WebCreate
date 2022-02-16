package com.estherNmorga.demo.controll;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieSetting {
	
	// 設定新cookie
	@RequestMapping(value="/setCookie", method = RequestMethod.GET)
	public String setCookie(
			HttpServletResponse response) {
		
		Cookie cookie = new Cookie("username", "morga");
		Cookie cookie2 = new Cookie("testUsing", "esther");
		
		cookie.setMaxAge(60*60);

		// 設置HTTPS 安全的Cookie
		cookie.setSecure(true);
		cookie2.setSecure(true);
		
		// prevent cross-site scripting (XSS) attacks
		cookie.setHttpOnly(true);
		cookie2.setHttpOnly(true);

		response.addCookie(cookie);
		response.addCookie(cookie2);
		
		return "add Cookie";
	}
	
	// 獲得cookie的值
	@RequestMapping(value = "/getCookie", method = RequestMethod.GET)
	public String getCookie(
			@CookieValue(value = "username") String username) {
		return "Hello! " + username;
	}
	
	// 取得cookie全部的資料
	@RequestMapping(value = "/getCookies", method = RequestMethod.GET)
	public String getAllCookies(
			HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			return Arrays.stream(cookies)
					.map(cook -> cook.getName() + "=" + cook.getValue())
					.collect(Collectors.joining(","));
		}
		
		return "No Cookies";
	}
	
	// 刪除cookie的資料
	@RequestMapping(value = "/deleteCookie", method = RequestMethod.GET)
	public String deleteCookie(
			HttpServletResponse response) {
		
		Cookie cookie = new Cookie("username", null);
		
		cookie.setMaxAge(0);
		
		cookie.setSecure(true);
		
		cookie.setHttpOnly(true);
		
//		cookie.setPath("/deleteCookie");
		
		response.addCookie(cookie);
		
		return "delete Cookie(username)";
	}
	
	
}
