package br.com.eventoweb.domain.evento;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import br.com.eventoweb.domain.types.StatusSubmissao;

@Entity
@Table(name = "submissao")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Submissao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5464867861786974311L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_tema", nullable = false, insertable = true, updatable = false)
	private Tema tema;

	@ManyToOne
	@JoinColumn(name = "id_participante", nullable = false, insertable = true, updatable = false)
	private Participante participante;

	@Column(name = "status", nullable = true, insertable = true, updatable = true, length = 16)
	@Enumerated(EnumType.STRING)
	private StatusSubmissao status;
	
	@OneToMany(mappedBy = "submissao", cascade = CascadeType.ALL)
	private List<SubmissaoArquivo> arquivos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public List<SubmissaoArquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<SubmissaoArquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public StatusSubmissao getStatus() {
		return status;
	}

	public void setStatus(StatusSubmissao status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Submissao) {
			final Submissao c = (Submissao) arg0;
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
