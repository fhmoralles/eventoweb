package br.com.eventoweb.domain.evento;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "patrocinio")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Patrocinio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4623941697669947346L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "descricao", nullable = true, insertable = true, updatable = true, length = 4096)
	private String descricao;

	@Column(name = "valorpatrocinio", nullable = false, insertable = true, updatable = false)
	private BigDecimal valorPatrocinio;

	@ManyToOne
	@JoinColumn(name = "id_participante", nullable = false, insertable = true, updatable = false)
	private Participante participante;

	@ManyToOne
	@JoinColumn(name = "id_patrociniotipo", nullable = false, insertable = true, updatable = false)
	private PatrocinioTipo patrocinioTipo;

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

	public BigDecimal getValorPatrocinio() {
		return valorPatrocinio;
	}

	public void setValorPatrocinio(BigDecimal valorPatrocinio) {
		this.valorPatrocinio = valorPatrocinio;
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

		if (arg0 instanceof Patrocinio) {
			final Patrocinio c = (Patrocinio) arg0;
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
