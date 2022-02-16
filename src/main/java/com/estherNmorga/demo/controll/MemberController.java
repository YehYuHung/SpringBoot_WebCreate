package com.estherNmorga.demo.controll;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
		Optional<String> optional = memberService.register(memberModel);
		if(optional.isPresent())
		{
			redirectAttributes.addFlashAttribute("MESSAGE", optional.get());
			return "redirect:register";
		}
		String message = optional.orElse("\u8a3b\u518a\u6210\u529f");
		redirectAttributes.addFlashAttribute("MESSAGE", message);
		return "redirect:login";
	}

	
	@GetMapping(value = "/information")
	public String information(
			@ModelAttribute(value = "MESSAGE") String message) {
		return "information";
	}
	
}
