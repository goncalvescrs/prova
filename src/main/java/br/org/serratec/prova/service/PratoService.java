package br.org.serratec.prova.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.prova.dto.PratoDto;
import br.org.serratec.prova.model.Prato;
import br.org.serratec.prova.model.Tipo;
import br.org.serratec.prova.repository.PratoRepository;

@Service
public class PratoService {
	
	@Autowired
    private PratoRepository repository;
	
    public List<PratoDto> buscarTodos() {
        return repository.findAll().stream()
                .map(p -> new PratoDto(p.getId(), p.getNome(),
                        p.getDescricao(), p.getPreco(), p.getTipo()))
                .toList();
    }
    
    public Optional<PratoDto> buscarPorId(Long id) {
        Optional<Prato> prato = repository.findById(id);

        if (prato.isPresent()) {
            return Optional.of(prato.get().toDTO());
        }
        return Optional.empty();
    }

    public PratoDto cadastrar(PratoDto prato) {
        Prato pratoEntity = prato.toEntity();
        repository.save(pratoEntity);
        return pratoEntity.toDTO();
    }

    public Optional<PratoDto> alterar(Long id, PratoDto prato) {
        Prato pratoEntity = prato.toEntity();

        if (repository.existsById(id)) {
        	pratoEntity.setId(id);
            repository.save(pratoEntity);
            return Optional.of(pratoEntity.toDTO());
        }
        return Optional.empty();
    }

    public Boolean deletar(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
    
    
    public List<PratoDto> buscarPorTipo(Tipo tipo) {
        return repository.findByTipo(tipo).stream().map(p -> new PratoDto(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getTipo())).collect(Collectors.toList());
    }
}
