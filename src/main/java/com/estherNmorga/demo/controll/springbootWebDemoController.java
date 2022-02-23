package com.estherNmorga.demo.controll;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estherNmorga.demo.model.OutputInfo;
import com.estherNmorga.demo.model.WebDemoModel;

@RestController
public class springbootWebDemoController {
	
	private static final String template = "Hello, %s!";
	// automatic increment/decrement number and get, not used to replace Long with this Object. 
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public WebDemoModel getWebDemo(@RequestParam(value = "name", defaultValue = "world") String name) {
		return new WebDemoModel(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/testCons")
	public OutputInfo getConstDemo(@RequestParam(value = "name", defaultValue = "world") String name) {
		com.estherNmorga.demo.model.Base data = new WebDemoModel(counter.incrementAndGet(), String.format(template, name));
		return new OutputInfo("GetMessageTest", data, "success");
	}
}
