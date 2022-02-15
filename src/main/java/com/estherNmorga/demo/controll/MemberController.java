package com.estherNmorga.demo.controll;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estherNmorga.demo.model.MemberModel;
import com.estherNmorga.demo.service.MemberService;

@CrossOrigin
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping(value = "/welcome")
	public String welcomeTest() {
		return "Welcome";
	}
	
	@GetMapping(value = {"/", "/login"})
	public String login(
			@ModelAttribute MemberModel memberModel,
			@ModelAttribute(value = "MESSAGE") String message) {
		return "login";
	}
	
	@PostMapping(value = "/login")
	public String doLogin(
			@ModelAttribute MemberModel memberModel,
			HttpSession session, 
			RedirectAttributes redirectAttributes) {
		//TODO: process POST request
		MemberModel loginUser = memberService.login(memberModel);
		if( loginUser == null )
		{
			String message = "帳號或密碼錯誤";
			redirectAttributes.addFlashAttribute("MESSAGE", message);
			return "redirect:login";
		}
		
		session.setAttribute("member", loginUser);
		return "redirect:information";
	}

	@GetMapping(value = "/register")
	public String register(@ModelAttribute MemberModel memberModel) {
		return "register";
	}
	
	@PostMapping(value = "/register")
	public String doLogin(@ModelAttribute MemberModel memberModel){
		//TODO: process POST request
		
		return "";
	}

	
	@GetMapping(value = "/information")
	public String information(
			@ModelAttribute(value = "MESSAGE") String Message) {
		return "information";
	}
	
}
