package br.com.eventoweb.domain.evento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.eventoweb.domain.types.StatusSubmissao;

@Entity
@Table(name = "submissaoavaliacao")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class SubmissaoAvaliacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7001645657764842358L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_submissao", nullable = false, insertable = true, updatable = false)
	private Submissao submissao;

	@ManyToOne
	@JoinColumn(name = "id_comitemembro", nullable = false, insertable = true, updatable = false)
	private ComiteMembro comiteMembro;

	@Column(name = "status", nullable = true, insertable = true, updatable = true, length = 16)
	@Enumerated(EnumType.STRING)
	private StatusSubmissao status;

	public SubmissaoAvaliacao() {
		super();
	}

	public SubmissaoAvaliacao(Submissao submissao) {
		super();
		this.submissao = submissao;
		this.status = StatusSubmissao.AGUARDANDO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Submissao getSubmissao() {
		return submissao;
	}

	public void setSubmissao(Submissao submissao) {
		this.submissao = submissao;
	}

	public ComiteMembro getComiteMembro() {
		return comiteMembro;
	}

	public void setComiteMembro(ComiteMembro comiteMembro) {
		this.comiteMembro = comiteMembro;
	}

	public StatusSubmissao getStatus() {
		return status;
	}

	public void setStatus(StatusSubmissao status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof SubmissaoAvaliacao) {
			final SubmissaoAvaliacao c = (SubmissaoAvaliacao) arg0;
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
