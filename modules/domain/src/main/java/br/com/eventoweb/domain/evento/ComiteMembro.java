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

import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.domain.types.TipoParticipante;

@Entity
@Table(name = "comitemembro")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class ComiteMembro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2679900829287594844L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_comite", nullable = false, insertable = true, updatable = false)
	private Comite comite;
	
	@ManyToOne
	@JoinColumn(name = "id_participante", nullable = false, insertable = true, updatable = false)
	private Participante participante;

	@Column(name = "tipocomitemembro", nullable = false, insertable = true, updatable = false)
	@Enumerated(EnumType.STRING)
	private TipoComiteMembro tipoComiteMembro;
	
	public ComiteMembro() {
		super();
	}

	public ComiteMembro(Comite comite) {
		super();
		this.comite = comite;
		this.setParticipante(new Participante());
		this.getParticipante().setTipoParticipante(TipoParticipante.COMITE);
		this.getParticipante().setEvento(comite.getTema().getEvento());
		this.setTipoComiteMembro(TipoComiteMembro.MEMBRO);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Comite getComite() {
		return comite;
	}

	public void setComite(Comite comite) {
		this.comite = comite;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	
	public TipoComiteMembro getTipoComiteMembro() {
		return tipoComiteMembro;
	}

	public void setTipoComiteMembro(TipoComiteMembro tipoComiteMembro) {
		this.tipoComiteMembro = tipoComiteMembro;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof ComiteMembro) {
			final ComiteMembro c = (ComiteMembro) arg0;
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
