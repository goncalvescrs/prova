package br.org.serratec.prova.dto;

import java.math.BigDecimal;

import br.org.serratec.prova.model.Prato;
import br.org.serratec.prova.model.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public record PratoDto(
	    Long id,
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull @PositiveOrZero BigDecimal preco,
        @NotNull Tipo tipo) {

    public Prato toEntity() {
        return new Prato(this.id, this.nome, this.descricao, this.preco, this.tipo);
    }
}
