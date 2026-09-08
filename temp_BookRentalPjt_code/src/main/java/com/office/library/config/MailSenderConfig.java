package com.office.library.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class MailSenderConfig {
	
final private String CLASS_NAME = "[MailSenderConfig] ";
	
	@Bean
	public JavaMailSenderImpl mailSenderImpl() {
		System.out.println(CLASS_NAME.concat("mailSenderImpl()"));
		
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		
		mailSenderImpl.setHost("smtp.gmail.com");
		mailSenderImpl.setPort(587);
		mailSenderImpl.setUsername("ho8566z@gmail.com");
		mailSenderImpl.setPassword("hlvzjdqblmaqokyf");
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		mailSenderImpl.setJavaMailProperties(properties);
		
		return mailSenderImpl;
		
	}

}
