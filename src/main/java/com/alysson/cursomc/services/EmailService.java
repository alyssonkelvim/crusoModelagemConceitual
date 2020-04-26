package com.alysson.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.alysson.cursomc.domain.Cliente;
import com.alysson.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj) ;
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
	
}
