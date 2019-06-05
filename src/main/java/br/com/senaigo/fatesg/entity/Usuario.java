package br.com.senaigo.fatesg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="[usuario]")
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario extends ResourceSupport implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable=false, unique = true)
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "dataAlteracaoSenha")
	@Temporal(TemporalType.DATE)
	private Date dataAlteracaoSenha;
	
	@Column(name = "dataCriacao")
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Column(name = "dataUltimoAcesso")
	@Temporal(TemporalType.DATE)
	private Date dataUltimoAcesso;
	
	@Column(name = "ativo")
	private boolean ativo;
	
}
