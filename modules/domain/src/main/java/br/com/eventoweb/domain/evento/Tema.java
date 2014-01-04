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
@Table(name = "tema")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Tema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3152970970760354762L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "descricao", nullable = false, insertable = true, updatable = true, length = 50)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_evento", nullable = false, insertable = true, updatable = false)
	private Evento evento;

	public Tema() {
		super();
	}

	public Tema(Evento e) {
		super();
		this.setEvento(e);
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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Tema) {
			final Tema c = (Tema) arg0;
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
