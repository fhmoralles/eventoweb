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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.types.TipoParticipante;

@Entity
@Table(name = "participante")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Participante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6666408568350772589L;
	
	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_evento", nullable = false, insertable = true, updatable = false)
	private Evento evento;
	
	@OneToOne
	@JoinColumn(name = "id_cadastro", nullable = false, insertable = true, updatable = false)
	private Cadastro cadastro;

	@Column(name = "tipoparticipante", nullable = false, insertable = true, updatable = true, length = 20)
	@Enumerated(EnumType.STRING)
	private TipoParticipante tipoParticipante;

	@OneToOne(mappedBy = "participante")
	private Financeiro financeiro;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public TipoParticipante getTipoParticipante() {
		return tipoParticipante;
	}

	public void setTipoParticipante(TipoParticipante tipoParticipante) {
		this.tipoParticipante = tipoParticipante;
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Participante) {
			final Participante c = (Participante) arg0;
			return new EqualsBuilder().append(this.getId(),
					c.getId()).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).toHashCode();
	}

	@Override
	public String toString() {
		return "Participante [id=" + id + ", evento=" + evento + ", cadastro="
				+ cadastro + ", tipoParticipante=" + tipoParticipante
				+ ", financeiro=" + financeiro + "]";
	}
	
}
