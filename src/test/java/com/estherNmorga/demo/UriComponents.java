package com.estherNmorga.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponents {
	
	@Test
	public void Uri() {
		System.out.println("-----------------------URI-----------------------");
		org.springframework.web.util.UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("www.test2.com")
				.path("/{template}")
				.queryParam("query", "a")
				.build();

		System.out.println(uriComponents.expand("gggg").toString());
		System.out.println(uriComponents.expand("ggGG").toUriString());
		System.out.println(uriComponents.expand("GGGG").toUri());
		System.out.println(uriComponents.toUri());
	}
	
	@Test
	public void responseEntityTest() {
		System.out.println("-----------------------responseEntityTest-----------------------");
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity("https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html", String.class);
		 String body = entity.getBody();
		 MediaType contentType = entity.getHeaders().getContentType();
		 HttpStatus statusCode = entity.getStatusCode();
//		 System.out.println(body);
		 System.out.println(contentType);
		 System.out.println(statusCode);
	}
}
