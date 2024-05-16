package br.org.serratec.prova.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.prova.dto.ClienteDto;
import br.org.serratec.prova.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/japa/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteServico;
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> obterTodosOsClientes(){
		return ResponseEntity.ok(clienteServico.obterTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> obterPorId(@PathVariable Long id){
		Optional<ClienteDto> cliente = clienteServico.obterPorId(id);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.badRequest().build();	
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<ClienteDto>> obterClientePorNome(@RequestBody String nome){
		return ResponseEntity.ok(clienteServico.obterPorNome(nome));

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDto cadastrarCliente(@Valid @RequestBody ClienteDto cliente){
		return clienteServico.salvar(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> alterarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteAlterado){
		Optional<ClienteDto> cliente = clienteServico.atualizar(id, clienteAlterado);
		
		if(cliente.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(cliente.get());
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteDto> deletarCliente(@PathVariable Long id){
		
		if(!clienteServico.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	
	
}
