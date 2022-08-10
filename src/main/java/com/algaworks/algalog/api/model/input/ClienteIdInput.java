package com.algaworks.algalog.api.model.input;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdInput {
	@NotNull
	private Long id;
}
