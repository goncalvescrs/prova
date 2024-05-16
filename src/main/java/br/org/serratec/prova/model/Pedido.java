package br.org.serratec.prova.model;

import java.math.BigDecimal;
import java.util.List;

import br.org.serratec.prova.dto.PedidoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "pedidos")
public class Pedido {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
	private Long id;
	private String nomeCliente;
	private List<String> pratos;
    private BigDecimal total;
    
    public Pedido () {}
    
	public Pedido(Long id, String nomeCliente, List<String> pratos, BigDecimal total) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.pratos = pratos;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public List<String> getPratos() {
		return pratos;
	}

	public void setPratos(List<String> pratos) {
		this.pratos = pratos;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
    
	public PedidoDto toDTO(){
        return new PedidoDto(this.id, this.nomeCliente, this.pratos, this.total);
    }
	
}
