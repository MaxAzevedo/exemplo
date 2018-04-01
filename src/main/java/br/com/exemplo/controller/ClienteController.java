package br.com.exemplo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.exception.ResourceNotFoundException;
import br.com.exemplo.model.Cliente;
import br.com.exemplo.repository.ClienteRepository;

/**
 * 
 * @author Max
 *
 */
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
    ClienteRepository clienteRepository;
	
	// buscar lista de clientes
	@GetMapping("/clientes")
	public List<Cliente> listarClintes() {
		return clienteRepository.findAll();
	}
	
	// buscar cliente por email
	@GetMapping("/cliente")
	public ResponseEntity<Cliente> buscarCliente(@RequestParam(value = "email") String email) {
		Cliente cliente = clienteRepository.buscarCliente(email);
		if(cliente == null) {
			throw new ResourceNotFoundException("Contato", "Email", email);
		}
		return ResponseEntity.ok().body(cliente);
	}
	
	// cadastrar um novo cliente
	@PostMapping("/cliente")
	public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody Cliente cliente) {
		Cliente clientePersistido = clienteRepository.buscarCliente(cliente.getEmail());
		if (clientePersistido == null) {
			return ResponseEntity.ok(clienteRepository.save(cliente));
		}
		return ResponseEntity.badRequest().build();
	}
	
	// editar cliente
	@PutMapping("/cliente")
	public ResponseEntity<Cliente> editarCliente(@Valid @RequestBody Cliente cliente) {
	    Cliente clientePersistido = clienteRepository.buscarCliente(cliente.getEmail());
	    if(clientePersistido == null) {
	    	throw new ResourceNotFoundException("Contato", "Email", cliente);
	    }
	    clientePersistido.setNome(cliente.getNome());
	    clientePersistido.setEmail(cliente.getEmail());

	    Cliente clienteAtualizado = clienteRepository.save(clientePersistido);
	    return ResponseEntity.ok(clienteAtualizado);
	}
	
	// deletar cliente
	@DeleteMapping("/cliente")
	public ResponseEntity<Cliente> excluirCliente(@RequestParam(value = "email") String email) {
		Cliente clientePersistido = clienteRepository.buscarCliente(email);
		
		if(clientePersistido == null) {
	        throw new ResourceNotFoundException("Contato", "Email", email);
	    }
		clienteRepository.delete(clientePersistido);
		return ResponseEntity.ok().build();
	}

}
