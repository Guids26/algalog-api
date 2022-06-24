package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ClienteController {

	private ClienteRepository clienteRepository;

	@GetMapping("/clientes")
	public List<Cliente> listar(HttpServletResponse response) {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED) 
	public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente) {
		List<Cliente>clienteExistentesComEmail = clienteRepository.findByEmail(cliente.getEmail());
		//System.out.println(clienteExistentesComEmail.isEmpty());
		if(clienteExistentesComEmail.isEmpty()) {
			return ResponseEntity.ok(clienteRepository.save(cliente));
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PutMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
		
	}
	
	@DeleteMapping("/cliente/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(clienteId);
		System.out.println(clienteId);
		return ResponseEntity.noContent().build();
	}

}
