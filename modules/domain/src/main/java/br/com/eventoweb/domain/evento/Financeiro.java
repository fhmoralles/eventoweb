package br.com.eventoweb.domain.evento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.eventoweb.domain.types.TipoFinanceiro;

@Entity
@Table(name = "financeiro")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Financeiro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2443592715709020516L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "descricao", nullable = true, insertable = true, updatable = true, length = 255)
	private String descricao;

	@Column(name = "valorfinanceiro", nullable = false, insertable = true, updatable = false)
	private BigDecimal valorFinanceiro;

	@Column(name = "tipofinanceiro", nullable = false, insertable = true, updatable = false)
	@Enumerated(EnumType.STRING)
	private TipoFinanceiro tipoFinanceiro;

	@Column(name = "dataprevisto", nullable = true, insertable = true, updatable = true)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataPrevisto;

	@Column(name = "datarealizado", nullable = true, insertable = true, updatable = true)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataRealizado;

	@ManyToOne
	@JoinColumn(name = "id_evento", nullable = false, insertable = true, updatable = false)
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name = "id_participante", nullable = false, insertable = true, updatable = false)
	private Participante participante;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorFinanceiro() {
		return valorFinanceiro;
	}

	public void setValorFinanceiro(BigDecimal valorFinanceiro) {
		this.valorFinanceiro = valorFinanceiro;
	}

	public TipoFinanceiro getTipoFinanceiro() {
		return tipoFinanceiro;
	}

	public void setTipoFinanceiro(TipoFinanceiro tipoFinanceiro) {
		this.tipoFinanceiro = tipoFinanceiro;
	}

	public Date getDataPrevisto() {
		return dataPrevisto;
	}

	public void setDataPrevisto(Date dataPrevisto) {
		this.dataPrevisto = dataPrevisto;
	}

	public Date getDataRealizado() {
		return dataRealizado;
	}

	public void setDataRealizado(Date dataRealizado) {
		this.dataRealizado = dataRealizado;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Financeiro) {
			final Financeiro c = (Financeiro) arg0;
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
