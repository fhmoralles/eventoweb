package br.com.eventoweb.domain.evento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.eventoweb.domain.types.TipoInscricao;

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

	@Column(name = "descricao", nullable = false, insertable = true, updatable = true, length = 1024)
	private String descricao;

	@Column(name = "datainicio", nullable = false, insertable = true, updatable = true)
	@Temporal(value = TemporalType.DATE)
	private Date dataInicio;

	@Column(name = "datacadastro", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "tipoinscricao", nullable = true, insertable = true, updatable = true, length = 16)
	@Enumerated(EnumType.STRING)
	private TipoInscricao tipoInscricao;

	@Column(name = "datafim", nullable = true, insertable = true, updatable = true)
	@Temporal(value = TemporalType.DATE)
	private Date dataFim;

	@Column(name = "sobre", nullable = false, insertable = true, updatable = true, length = 4096)
	private String sobre;

	@Column(name = "datasubmissao", nullable = false, insertable = true, updatable = true)
	@Temporal(value = TemporalType.DATE)
	private Date dataSubmissao;
	
	@OneToMany(mappedBy = "evento", fetch = FetchType.EAGER)
	private List<Participante> participantes; 
	
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public TipoInscricao getTipoInscricao() {
		return tipoInscricao;
	}

	public void setTipoInscricao(TipoInscricao tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public Date getDataSubmissao() {
		return dataSubmissao;
	}

	public void setDataSubmissao(Date dataSubmissao) {
		this.dataSubmissao = dataSubmissao;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Evento) {
			final Evento c = (Evento) arg0;
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
