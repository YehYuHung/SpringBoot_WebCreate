package com.estherNmorga.demo.controll;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estherNmorga.demo.model.OutputInfo;
import com.estherNmorga.demo.model.WebDemoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.estherNmorga.demo.Config.SpringSwaggerConfig;

@CrossOrigin
@RestController
@Api(tags = {SpringSwaggerConfig.Greeting})
public class webDemoController {
	
	private static final String template = "Hello, %s!";
	// automatic increment/decrement number and get, not used to replace Long with this Object. 
	private final AtomicLong counter = new AtomicLong();

	@ApiOperation(tags = "GreetingPart", value = "測試Get 回傳", notes = "該使用方法為測試項目 - 返回json格式做測試")
	@GetMapping("/greeting")
	public WebDemoModel getWebDemo(
			@ApiParam(value = "請輸入想稱呼對方的名稱")
			@RequestParam(value = "name", defaultValue = "world") String name) {
		return new WebDemoModel(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/testCons")
	public OutputInfo getConstDemo(@RequestParam(value = "name", defaultValue = "world") String name) {
		com.estherNmorga.demo.model.Base data = new WebDemoModel(counter.incrementAndGet(), String.format(template, name));
		return new OutputInfo("GetMessageTest", data, "success");
	}
}
