package br.com.senaigo.fatesg.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="[justificativa_ponto]")
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Justificativa extends ResourceSupport{	
	
	
	@Id
	@GeneratedValue(generator="justificativaAbono_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="justificativaAbono_seq", sequenceName="justificativaAbono_seq", allocationSize=1, initialValue=1)
	@Column(name="id")
	private Integer idJustificativa;
	
	private String titulo;
	private String descricao;
	@Lob
	private byte[] anexoDocumento;
	private String horasDiariaInicio;
	private String horasDiariaTermino;
	private String dataInicio;
	private String dataTermino;
	private String status;
	@Temporal(TemporalType.DATE)
	private Date data;
	private Long idUser;
}
