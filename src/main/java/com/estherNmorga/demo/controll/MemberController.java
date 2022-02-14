package com.estherNmorga.demo.controll;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	@GetMapping(value = "/")
	public String loginWebPage() {
		return "Welcome";
	}
}
