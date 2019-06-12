package br.com.senaigo.fatesg.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegistroPonto extends ResourceSupport implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long idRegistro;	
	private String data;
	private Long idFuncionario;
	private Long idcodigoJornadaTrabalho;
	private String email;
	private String primeiraEntrada;
	private String primeiraSaida;
	private String segundaEntrada;
	private String segundaSaida;
	private Long saldo;
	
	
}
