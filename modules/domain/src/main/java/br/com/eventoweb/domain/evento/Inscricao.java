package br.com.eventoweb.domain.evento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "inscricao")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Inscricao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7270644149515792551L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_evento", nullable = false, insertable = true, updatable = false)
	private Evento evento;

	@Column(name = "descricao", nullable = true, insertable = true, updatable = true, length = 4096)
	private String descricao;

	@Column(name = "valorinscricao", nullable = false, insertable = true, updatable = false)
	private BigDecimal valorInscricao;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "inscricaoatividade", joinColumns = { @JoinColumn(name = "id_inscricao") }, inverseJoinColumns = { @JoinColumn(name = "id_atividade") })
	private List<Atividade> atividades;

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

	public BigDecimal getValorInscricao() {
		return valorInscricao;
	}

	public void setValorInscricao(BigDecimal valorInscricao) {
		this.valorInscricao = valorInscricao;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Inscricao) {
			final Inscricao c = (Inscricao) arg0;
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
