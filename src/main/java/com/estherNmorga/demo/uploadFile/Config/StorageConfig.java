package com.estherNmorga.demo.uploadFile.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estherNmorga.demo.uploadFile.service.StorageService;

@Configuration
public class StorageConfig {
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {			
//			storageService.deleteAll();
			storageService.init();
		};
	}
}
