package com.alysson.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alysson.cursomc.services.DBService;
import com.alysson.cursomc.services.EmailService;
import com.alysson.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		if(strategy.equals("create")) {
			dbService.instatiateTestDatabase();
			return true;
		}
		return false;
		
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
