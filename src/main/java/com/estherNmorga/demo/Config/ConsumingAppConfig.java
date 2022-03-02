package com.estherNmorga.demo.Config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.estherNmorga.demo.model.weather.WeatherModel;
import com.estherNmorga.demo.uploadFile.service.StorageService;

@Configuration
public class ConsumingAppConfig {
	
	private static final Logger log = LoggerFactory.getLogger(ConsumingAppConfig.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	// 注意，Url必須透過Url解析網址還原，否則restTemplate找不到資料
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
		return args -> {
			WeatherModel weather = restTemplate.getForObject("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-0ECF92FF-1305-4B2E-8E01-2FFA1C4759FA&limit=10&offset=0&format=JSON&locationName=屏東縣&elementName=PoP&sort=time", WeatherModel.class);
			log.info(weather.toString());
		};
	}
	
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
}
