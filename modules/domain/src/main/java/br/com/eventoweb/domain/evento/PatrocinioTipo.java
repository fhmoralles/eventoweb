package br.com.eventoweb.domain.evento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "patrociniotipo")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class PatrocinioTipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1874158705287572534L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "descricao", nullable = true, insertable = true, updatable = true, length = 4096)
	private String descricao;

	@Column(name = "valorpadrao", nullable = false, insertable = true, updatable = false)
	private BigDecimal valorPadrao;

	@ManyToOne
	@JoinColumn(name = "id_evento", nullable = false, insertable = true, updatable = false)
	private Evento evento;
	
	@OneToMany(mappedBy = "patrocinioTipo", cascade = CascadeType.ALL)
	private List<PatrocinioExposicao> exposicoes;

	@OneToMany(mappedBy = "patrocinioTipo", cascade = CascadeType.ALL)
	private List<PatrocinioAtividade> atividades;
	
	public PatrocinioTipo() {
		super();
	}

	public PatrocinioTipo(Evento evento) {
		this.setEvento(evento);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorPadrao() {
		return valorPadrao;
	}

	public void setValorPadrao(BigDecimal valorPadrao) {
		this.valorPadrao = valorPadrao;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<PatrocinioExposicao> getExposicoes() {
		return exposicoes;
	}

	public void setExposicoes(List<PatrocinioExposicao> exposicoes) {
		this.exposicoes = exposicoes;
	}

	public List<PatrocinioAtividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<PatrocinioAtividade> atividades) {
		this.atividades = atividades;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof PatrocinioTipo) {
			final PatrocinioTipo c = (PatrocinioTipo) arg0;
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
