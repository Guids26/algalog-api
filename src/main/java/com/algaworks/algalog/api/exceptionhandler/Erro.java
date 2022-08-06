package com.algaworks.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Erro {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String descricao;
	private List<Campo> campos;
	
	@AllArgsConstructor
	@Getter
	public static class Campo{
		private String nome;
		private String mensagem;
	}

}
