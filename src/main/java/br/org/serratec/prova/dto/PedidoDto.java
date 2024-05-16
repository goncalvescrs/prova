package br.org.serratec.prova.dto;

import java.math.BigDecimal;
import java.util.List;

import br.org.serratec.prova.model.Pedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoDto(
		Long id,
		@NotBlank
		String nomeCliente,
		@NotBlank
		List<String> pratos,
		@NotNull
	    BigDecimal total
		) {
	
	public Pedido toEntity() {
        return new Pedido(this.id, this.nomeCliente, this.pratos, this.total);
    }
}
