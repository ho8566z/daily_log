package com.office.library.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class jdbcTemplateConfig {

	final private String CLASS_NAME = "[jdbcTemplateConfig] ";
	
	@Bean
	public BasicDataSource dataSource() {
		System.out.println(CLASS_NAME.concat("basicDataSource()"));
		
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://localhost:3306/db_library");
		basicDataSource.setUsername("root");
		basicDataSource.setPassword("1234");
		
		basicDataSource.setInitialSize(5);
		basicDataSource.setMaxTotal(10);
		basicDataSource.setMaxIdle(2);
		basicDataSource.setMinIdle(2);
		
		return basicDataSource;

	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		System.out.println(CLASS_NAME.concat("jdbcTemplate()"));
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		
		return jdbcTemplate;
		
		
	}
	
}
