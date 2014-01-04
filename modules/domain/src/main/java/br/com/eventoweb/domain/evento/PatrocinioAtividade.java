package br.com.eventoweb.domain.evento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "patrocinioatividade")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class PatrocinioAtividade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8138574361671740528L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_patrociniotipo", nullable = false, insertable = true, updatable = false)
	private PatrocinioTipo patrocinioTipo;

	@ManyToOne
	@JoinColumn(name = "id_atividade", nullable = false, insertable = true, updatable = false)
	private Atividade atividade;
	
	@Column(name = "quantidade", nullable = true, insertable = true, updatable = true, length = 4096)
	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PatrocinioTipo getPatrocinioTipo() {
		return patrocinioTipo;
	}

	public void setPatrocinioTipo(PatrocinioTipo patrocinioTipo) {
		this.patrocinioTipo = patrocinioTipo;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof PatrocinioAtividade) {
			final PatrocinioAtividade c = (PatrocinioAtividade) arg0;
			return new EqualsBuilder().append(this.getId(), c.getId())
					.isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).toHashCode();
	}
	
}
