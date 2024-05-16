package br.org.serratec.prova.dto;

import java.math.BigDecimal;
import java.util.List;

import br.org.serratec.prova.model.Pedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoDto(
		Long id,
		@NotBlank
		String nome,
		@NotNull
		List<String> pratos,
		@NotNull
	    BigDecimal total
		) {
	
	public Pedido toEntity() {
        return new Pedido(this.id, this.nome, this.pratos, this.total);
    }
}
