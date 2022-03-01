package com.estherNmorga.demo.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// propertySource 可設定外部properties加載
// 若無，則自動從application.properties搜索
// 找不到不會報錯，只會將值賦予null的值
@PropertySource("classpath:personal.properties")

@ConfigurationProperties(prefix = "personal", ignoreUnknownFields = true)

// 搭配 Configuration 加載，若無則需要使用 @EnableConfigurationProperties在Application.java上強制加載
@Configuration

// 注意！ 下方結構需對應properites檔，若無則找不到相對應結構資料
public class StorageProperties {
	
	private String location;
	private String password;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "{location=" + location + ", password=" + password + "}";
	}
	
}
