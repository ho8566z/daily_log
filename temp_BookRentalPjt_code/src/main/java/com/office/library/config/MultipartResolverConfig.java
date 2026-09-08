package com.office.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class MultipartResolverConfig {
	
	final private String CLASS_NAME = "[MultipartResolverConfig] ";
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		System.out.println(CLASS_NAME.concat("multipartResolver()"));
		
		CommonsMultipartResolver commonsMultipartResolver = 
				new CommonsMultipartResolver();
		
		commonsMultipartResolver.setMaxUploadSize(10240000);
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		
		return commonsMultipartResolver;
		
	}

}
