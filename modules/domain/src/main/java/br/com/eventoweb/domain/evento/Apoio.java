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

import br.com.eventoweb.domain.types.TipoParticipante;

@Entity
@Table(name = "apoio")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Apoio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4623941697669947346L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_participante", nullable = false, insertable = true, updatable = false)
	private Participante participante;

	@ManyToOne
	@JoinColumn(name = "id_patrociniotipo", nullable = false, insertable = true, updatable = false)
	private PatrocinioTipo patrocinioTipo;

	
	public Apoio() {
		super();
	}

	public Apoio(Evento e) {
		super();
		this.setParticipante(new Participante());
		this.getParticipante().setTipoParticipante(TipoParticipante.APOIADOR);
		this.getParticipante().setEvento(e);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public PatrocinioTipo getPatrocinioTipo() {
		return patrocinioTipo;
	}

	public void setPatrocinioTipo(PatrocinioTipo patrocinioTipo) {
		this.patrocinioTipo = patrocinioTipo;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Apoio) {
			final Apoio c = (Apoio) arg0;
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
