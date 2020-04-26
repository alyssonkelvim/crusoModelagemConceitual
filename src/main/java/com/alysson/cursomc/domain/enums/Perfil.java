package com.alysson.cursomc.domain.enums;

public enum Perfil {
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int id;
	private String descricao;

	private Perfil(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer id) {
		if (id == null) {
			return null;
		}else {
			for(Perfil ep: Perfil.values()) {
				if(id.equals(ep.getId())) {
					return ep;
				}
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + id);
	}

}
