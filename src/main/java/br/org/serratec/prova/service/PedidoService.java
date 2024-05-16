package br.org.serratec.prova.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.prova.dto.PedidoDto;
import br.org.serratec.prova.model.Pedido;
import br.org.serratec.prova.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository pedidoRepositorio;
	
	
	public List<PedidoDto> obterTodos(){
		return pedidoRepositorio.findAll().stream()
				.map(c -> new PedidoDto(c.getId(), c.getNome(), c.getPratos(), 
						c.getTotal())).toList();
	}
	
	public Optional<PedidoDto> obterPorId(Long id) {
		Optional<Pedido> pedido = pedidoRepositorio.findById(id);
		
		if(pedido.isPresent()) {
			return Optional.of(pedido.get().toDTO());
		}
		return Optional.empty();
	}

	public PedidoDto salvar(PedidoDto pedido) {
		Pedido pedidoEntity = pedidoRepositorio.save(pedido.toEntity());
		return pedidoEntity.toDTO();
	}
	
	public Optional<PedidoDto> atualizar(Long id, PedidoDto pedido){
		Pedido pedidoEntity = pedido.toEntity();
		
		if(pedidoRepositorio.existsById(id)) {
			pedidoEntity.setId(id);
			pedidoRepositorio.save(pedidoEntity);
			return Optional.of(pedidoEntity.toDTO());
		}
		return Optional.empty();
	}
	
	public boolean deletar(Long id) {
		if(!pedidoRepositorio.existsById(id)) {
			return false;
		}
		
		pedidoRepositorio.deleteById(id);
		return true;
	}
	
	public List<PedidoDto> obterPorNome(String nome) {
		return pedidoRepositorio.findByNomeContainingIgnoreCase(nome).stream()
				.map(c -> new PedidoDto(c.getId(), c.getNome(), c.getPratos(), 
						c.getTotal())).toList();
	}
}
