package com.estherNmorga.demo.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.estherNmorga.demo.model.weather.WeatherModel;

@Configuration
public class ConsuminAppConfig {
	
	private static final Logger log = LoggerFactory.getLogger(ConsuminAppConfig.class);
	
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
}
