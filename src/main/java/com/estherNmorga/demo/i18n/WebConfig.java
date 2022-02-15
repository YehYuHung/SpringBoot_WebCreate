package com.estherNmorga.demo.i18n;

import java.util.Locale;

import org.springframework.boot.autoconfigure.web.WebProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	// i18n 設定 ， 若properties 設定都失敗
//	@Bean
//	public ReloadableResourceBundleMessageSource messageSource() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		// classpath -> src/main/resource
//		// filePath  -> static/i18n/
//		// prefix 	 -> index
//		// suffix	 -> .properties
//		// "lang" in your country to get
//		messageSource.setBasename("classpath:static/i18n/index");
//		messageSource.setDefaultEncoding("UTF-8");
//		messageSource.setCacheSeconds(3600);
//		return messageSource;
//	}

	
    /**
     * 註冊locale解析器bean
     */
    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.TAIWAN);
        return localeResolver;
    }

    /**
     * 註冊locale攔截器bean
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang"); // use request param "lang" to change locale setting
        return localeChangeInterceptor;
    }

    /**
     * 註冊locale截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
