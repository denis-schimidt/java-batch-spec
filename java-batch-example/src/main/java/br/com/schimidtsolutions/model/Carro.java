package br.com.schimidtsolutions.model;

import java.io.Serializable;

public class Carro implements Serializable {
	private static final long serialVersionUID = 7188536695206510958L;
	private Long id;
	private String descricao;
	private Integer ano;
	
	public Carro(Long id, String descricao, Integer ano) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.ano = ano;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getAno() {
		return ano;
	}

	@Override
	public String toString() {
		return String.format("Carro [id=%s, descricao=%s, ano=%s]", id, descricao, ano);
	}
}
