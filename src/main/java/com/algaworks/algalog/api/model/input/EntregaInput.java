package com.algaworks.algalog.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaInput {
	@Valid
	@NotNull
	private ClienteIdInput cliente;
	@Valid
	@NotNull
	private DestinatarioInput destinatario;
	@NotNull
	private BigDecimal taxa;
}
