package com.estherNmorga.demo.controll;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estherNmorga.demo.model.MemberModel;
import com.estherNmorga.demo.service.MemberService;

@CrossOrigin
@Controller
public class MemberController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MemberService memberService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@GetMapping(value = "/welcome")
	public String welcomeTest() {
		return "Welcome";
	}
	
	@GetMapping(value = {"/", "/login"})
	public String login(
			Model model,
			@ModelAttribute MemberModel memberModel,
			@ModelAttribute(value = "MESSAGE") String message) {
		model.addAttribute("MESSAGE", message);
		return "login";
	}
	
	@PostMapping(value = "/login")
	public String doLogin(
			@Valid @ModelAttribute MemberModel memberModel,
			HttpSession session, 
			RedirectAttributes redirectAttributes) {
		//TODO: process POST request
		
		MemberModel loginUser = memberService.login(memberModel);
		logger.info(memberModel.getName() + " 登陸成功");
		session.setAttribute("member", loginUser);
		return "redirect:information";
	}

	@GetMapping(value = "/register")
	public String register(
			Model model,
			@ModelAttribute MemberModel memberModel,
			@ModelAttribute(value = "MESSAGE") String message) {
		model.addAttribute("MESSAGE", message);
		return "register";
	}
	
	@PostMapping(value = "/register")
	public String doRegister(
			@Valid @ModelAttribute MemberModel memberModel,
			RedirectAttributes redirectAttributes){
		//TODO: process POST request
		memberService.register(memberModel);
		logger.info(memberModel.getId() + " " + memberModel.getName() + " 註冊成功");
		redirectAttributes.addFlashAttribute("MESSAGE", "\u8a3b\u518a\u6210\u529f");
		return "register";
	}

	
	@GetMapping(value = "/information")
	public String information(
			@ModelAttribute(value = "MESSAGE") String message) {
		return "information";
	}
	
}
