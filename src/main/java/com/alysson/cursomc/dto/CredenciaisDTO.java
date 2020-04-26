package com.alysson.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class CredenciaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	private String email;
	@NotEmpty(message="Preenchimento Obrigatório")
	private String senha;
	
	public CredenciaisDTO() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}
