package br.org.serratec.prova.controller;

import java.util.List;

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

import br.org.serratec.prova.dto.PratoDto;
import br.org.serratec.prova.model.Tipo;
import br.org.serratec.prova.service.PratoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/japa")
public class PratoController {

	@Autowired
	private PratoService service;
	
	@GetMapping
	public ResponseEntity<List<PratoDto>> buscarTodosOsPratos() {
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PratoDto> buscarProdutoPorId(@PathVariable Long id) {
		return ResponseEntity.of(service.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PratoDto> cadastrar(@Valid @RequestBody PratoDto prato) {
		return ResponseEntity.ok(service.cadastrar(prato));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PratoDto> AlterarProduto(@Valid @PathVariable Long id, @RequestBody PratoDto produtoAlterado) {
		return ResponseEntity.of(service.alterar(id, produtoAlterado));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PratoDto> deletarProduto(@PathVariable Long id) {
		if(!service.deletar(id)){
            return ResponseEntity.notFound().build();
        };
        return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<PratoDto>> buscarPorTipo(@PathVariable String tipo){
		return ResponseEntity.ok(service.buscarPorTipo(Tipo.valueOf(tipo.toUpperCase())));
	}
	
}
