package br.org.serratec.prova.dto;

import br.org.serratec.prova.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteDto(
	Long id,
	@NotBlank
	String nome,
	@Size(min=11, max=11)
	@NotBlank
    @Pattern(regexp = "\\d{11}")
	String cpf,
	@NotBlank
	String telefone
	) {

	public Cliente toEntity() {
		return new Cliente(this.id, this.nome, this.cpf, this.telefone);
	}

}
