package br.com.eventoweb.domain.evento;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.eventoweb.domain.cadastro.Participante;

@Entity
@Table(name = "evento")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Evento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7757786889732070953L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "nome", nullable = false, insertable = true, updatable = true, length = 255)
	private String nome;

	@Column(name = "descricao", nullable = false, insertable = true, updatable = true, length = 255)
	private String descricao;

	@Column(name = "dataevento", nullable = false, insertable = true, updatable = true)
	@Temporal(value = TemporalType.DATE)
	private Date dataEvento;

	@Column(name = "datacadastro", nullable = false, insertable = true, updatable = true)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_participante", nullable = false, insertable = true, updatable = false)
	private Participante participante;
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	private List<Organizador> organizadores;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	private List<Local> locais;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public List<Organizador> getOrganizadores() {
		return organizadores;
	}

	public void setOrganizadores(List<Organizador> organizadores) {
		this.organizadores = organizadores;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Evento) {
			final Evento c = (Evento) arg0;
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
