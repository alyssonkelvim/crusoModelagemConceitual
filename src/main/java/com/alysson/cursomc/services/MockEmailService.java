package com.alysson.cursomc.services;

import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;



public class MockEmailService extends AbstractEmailService{

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de E-mail...");
		LOG.info(msg.toString());
		LOG.info("\n\nEmail enviado");
	}

}
