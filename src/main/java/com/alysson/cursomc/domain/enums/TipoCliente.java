package com.alysson.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int id;
	private String descricao;

	private TipoCliente(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer id) {
		if (id == null) {
			return null;
		}else {
			for(TipoCliente tp: TipoCliente.values()) {
				if(id.equals(tp.getId())) {
					return tp;
				}
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + id);
	}

}
