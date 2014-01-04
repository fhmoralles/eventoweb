package br.com.eventoweb.domain.evento;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "comite")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Comite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4902939341579798695L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "descricao", nullable = true, insertable = true, updatable = true, length = 4096)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_tema", nullable = false, insertable = true, updatable = false)
	private Tema tema;
	
	@OneToMany(mappedBy = "comite", fetch = FetchType.EAGER)
	private List<ComiteMembro> membros;
	
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

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<ComiteMembro> getMembros() {
		return membros;
	}

	public void setMembros(List<ComiteMembro> membros) {
		this.membros = membros;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Comite) {
			final Comite c = (Comite) arg0;
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
