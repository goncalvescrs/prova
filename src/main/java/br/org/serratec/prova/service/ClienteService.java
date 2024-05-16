package br.org.serratec.prova.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.prova.dto.ClienteDto;
import br.org.serratec.prova.model.Cliente;
import br.org.serratec.prova.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepositorio;
	
	public List<ClienteDto> obterTodos(){
		return clienteRepositorio.findAll().stream()
				.map(c -> new ClienteDto(c.getId(), c.getNome(), c.getCpf(), 
						c.getTelefone())).toList();
	}
	
	public Optional<ClienteDto> obterPorId(Long id) {
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		
		if(cliente.isPresent()) {
			return Optional.of(cliente.get().toDto());
		}
		return Optional.empty();
	}

	public ClienteDto salvar(ClienteDto cliente) {
		Cliente clienteEntity = clienteRepositorio.save(cliente.toEntity());
		return clienteEntity.toDto();
	}
	
	public Optional<ClienteDto> atualizar(Long id, ClienteDto cliente){
		Cliente clienteEntity = cliente.toEntity();
		
		if(clienteRepositorio.existsById(id)) {
			clienteEntity.setId(id);
			clienteRepositorio.save(clienteEntity);
			return Optional.of(clienteEntity.toDto());
		}
		return Optional.empty();
	}
	
	public boolean deletar(Long id) {
		if(!clienteRepositorio.existsById(id)) {
			return false;
		}
		
		clienteRepositorio.deleteById(id);
		return true;
	}
	
	
	public List<ClienteDto> obterPorNome(String nome) {
		return clienteRepositorio.findByNomeContainingIgnoreCase(nome).stream()
				.map(c -> new ClienteDto(c.getId(), c.getNome(), c.getCpf(), 
						c.getTelefone())).toList();
	}
	
	
}
