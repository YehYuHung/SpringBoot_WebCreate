package com.estherNmorga.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import springfox.documentation.service.Tag;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringSwaggerConfig {
	
	public static final String Greeting = "GGGGGGGG"; 
	
	// groupName -> 公司內都會有使用人員分組  A組用A 的 controller
	// 									B組用B 的 controller
	// 									C組用C 的 controller
	// 使用groupName 可以自動切換分組項目，讓一個頁面不用放下太多不必要的restFul API內容
	
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("public-api")
                .apiInfo(apiInfo())
                // 配置多個Tag Name/Description , TagName可以利用private final 參數固定在標頭上
                .tags(new Tag(Greeting, "Restful文件測試-greeting"))
                .select()
                //可指定單個package / 多個All
                // .apis(RequestHandlerSelectors.basePackage("com.estherNmorga.demo.controll"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())//paths(PathSelectors.regex("/.*"))
                .build();
    }
    
    @Bean
    public Docket createRestApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("public-api2")
                .apiInfo(apiInfo())
                .select()
                //可指定單個package / 多個All
                // .apis(RequestHandlerSelectors.basePackage("com.estherNmorga.demo.controll"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())//paths(PathSelectors.regex("/.*"))
                .build();
    }
    
    // 不包含 method內容 -> 上半部份敘述的內容
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2建構RESTful APIs -- 測試")
                .description("Morga.Yeh Spring boot 之 swagger 測試")
                .termsOfServiceUrl("https://hackmd.io/@Morga/r1V-rkjKt")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}