package br.com.eventoweb.domain.cadastro;

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
@Table(name = "espaco")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Espaco implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5816565193214500135L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_cadastro", nullable = false, insertable = true, updatable = false)
	private Cadastro cadastro;
	
	@Column(name = "descricao", nullable = false, insertable = true, updatable = true, length = 100)
	private String descricao;
	
	@Column(name = "capacidade", nullable = true, insertable = true, updatable = true)
	private Integer capacidade;
	
	@Column(name = "complemento", nullable = true, insertable = true, updatable = true, length = 255)
	private String complemento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Espaco) {
			final Espaco c = (Espaco) arg0;
			return new EqualsBuilder().append(this.getId(),
					c.getId()).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).toHashCode();
	}
	
}
