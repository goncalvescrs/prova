package br.org.serratec.prova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.prova.model.Prato;
import br.org.serratec.prova.model.Tipo;

public interface PratoRepository extends JpaRepository<Prato, Long>{

	List<Prato> findByTipo(Tipo tipo);
	
}
