package br.com.senaigo.fatesg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="[registro_ponto]")
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPonto extends ResourceSupport{	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer idRegistro;	
	private String data;
	private Long idFuncionario;
	private Long idcodigoJornadaTrabalho;
	private String primeiraEntrada;
	private String primeiraSaida;
	private String segundaEntrada;
	private String segundaSaida;
	private Long saldo;
	
	
}
