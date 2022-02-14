package com.estherNmorga.demo.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.estherNmorga.demo.model.MemberModel;

@Controller
public class MemberController {

	@GetMapping(value = "/welcome")
	public String welcomeTest() {
		return "Welcome";
	}
	
	@GetMapping(value = {"/", "/login"})
	public String login(@ModelAttribute MemberModel memberModel) {
		return "login";
	}
	
	@GetMapping(value = "/register")
	public String register(@ModelAttribute MemberModel memberModel) {
		return "register";
	}
}
