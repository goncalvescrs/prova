package br.org.serratec.prova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.org.serratec.prova.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNomeContainingIgnoreCase(String nome);
	
	
}
