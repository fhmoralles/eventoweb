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

import br.com.eventoweb.domain.cadastro.Espaco;
import br.com.eventoweb.domain.types.TipoAtividade;

@Entity
@Table(name = "atividade")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Atividade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7270644149515792551L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "tipoatividade", nullable = false, insertable = true, updatable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private TipoAtividade tipoAtividade;

	@Column(name = "titulo", nullable = false, insertable = true, updatable = true, length = 100)
	private String titulo;

	@Column(name = "descricao", nullable = true, insertable = true, updatable = true, length = 4096)
	private String descricao;

	@Column(name = "conteudo", nullable = true, insertable = true, updatable = true, length = 4096)
	private String conteudo;

	@Column(name = "horainicio", nullable = true, insertable = true, updatable = true, length = 5)
	private String horaInicio;

	@Column(name = "horafim", nullable = true, insertable = true, updatable = true, length = 5)
	private String horaFim;
	
	@Column(name = "valoratividade", nullable = false, insertable = true, updatable = false)
	private BigDecimal valorAtividade;

	@ManyToOne
	@JoinColumn(name = "id_participante", nullable = false, insertable = true, updatable = false)
	private Participante participante;

	@ManyToOne
	@JoinColumn(name = "id_espaco", nullable = false, insertable = true, updatable = false)
	private Espaco espaco;

	@Column(name = "dataatividade", nullable = true, insertable = true, updatable = true)
	@Temporal(value = TemporalType.DATE)
	private Date dataAtividade;

	@ManyToOne
	@JoinColumn(name = "id_tema", nullable = false, insertable = true, updatable = false)
	private Tema tema;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public BigDecimal getValorAtividade() {
		return valorAtividade;
	}

	public void setValorAtividade(BigDecimal valorAtividade) {
		this.valorAtividade = valorAtividade;
	}


	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Espaco getEspaco() {
		return espaco;
	}

	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}

	public Date getDataAtividade() {
		return dataAtividade;
	}

	public void setDataAtividade(Date dataAtividade) {
		this.dataAtividade = dataAtividade;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Atividade) {
			final Atividade c = (Atividade) arg0;
			return new EqualsBuilder().append(this.getId(), c.getId())
					.isEquals();
		}
		return false;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).toHashCode();
	}

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", tipoAtividade=" + tipoAtividade
				+ ", titulo=" + titulo + ", descricao=" + descricao
				+ ", conteudo=" + conteudo + ", horaInicio=" + horaInicio
				+ ", horaFim=" + horaFim + ", valorAtividade=" + valorAtividade
				+ ", participante=" + participante + ", espaco=" + espaco
				+ ", dataAtividade=" + dataAtividade + ", tema=" + tema + "]";
	}

}
