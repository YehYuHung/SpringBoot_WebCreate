package com.estherNmorga.demo.Service.Impl;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.estherNmorga.demo.Config.StorageProperties;
import com.estherNmorga.demo.service.StorageService;
import com.estherNmorga.demo.service.Impl.StorageServiceImpl;

// 記得添加 SpringBootTest，否則一般的JUnit不會有Context容器對Autowired注入物件
// 只會一直有error產生
@SpringBootTest
public class StorageServiceImplTest {
	
	@Autowired
	private StorageProperties properties;
	
	private StorageService service;

	@BeforeEach
	@Test
	public void init() {
//		properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
		service = new StorageServiceImpl(properties);
		service.init();
	}
}
