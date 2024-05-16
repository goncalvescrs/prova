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

import br.org.serratec.prova.dto.PedidoDto;
import br.org.serratec.prova.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/japa/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoDto>> buscarTodosOsPedidos() {
		return ResponseEntity.ok(pedidoService.obterTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> obterPorId(@PathVariable Long id){
		Optional<PedidoDto> pedido = pedidoService.obterPorId(id);
		
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.badRequest().build();	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDto cadastrarCliente(@Valid @RequestBody PedidoDto pedido){
		return pedidoService.salvar(pedido);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> alterarCliente(@PathVariable Long id, @RequestBody PedidoDto pedidoAlterado){
		Optional<PedidoDto> pedido = pedidoService.atualizar(id, pedidoAlterado);
		
		if(pedido.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(pedido.get());
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PedidoDto> deletarCliente(@PathVariable Long id){
		
		if(!pedidoService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<PedidoDto>> obterClientePorNome(@RequestBody String nome){
		return ResponseEntity.ok(pedidoService.obterPorNome(nome));

	}
}
