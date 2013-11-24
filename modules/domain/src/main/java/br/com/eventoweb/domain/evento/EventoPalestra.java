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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.eventoweb.domain.cadastro.LugarEspaco;
import br.com.eventoweb.domain.cadastro.Participante;

@Entity
@Table(name = "eventopalestra")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class EventoPalestra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7270644149515792551L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "titulo", nullable = false, insertable = true, updatable = true, length = 100)
	private String titulo;

	@Column(name = "descricao", nullable = false, insertable = true, updatable = true, length = 4096)
	private String descricao;

	@Column(name = "horainicio", nullable = false, insertable = true, updatable = true, length = 5)
	private String horaInicio;

	@Column(name = "horafim", nullable = false, insertable = true, updatable = true, length = 5)
	private String horaFim;
	
	@Column(name = "valorpalestrante", nullable = false, insertable = true, updatable = false)
	private BigDecimal valorPalestrante;

	@ManyToOne
	@JoinColumn(name = "id_evento", nullable = false, insertable = true, updatable = false)
	private Evento evento;

	@OneToOne
	@JoinColumn(name = "id_participante", nullable = false, insertable = true, updatable = false)
	private Participante participante;

	@ManyToOne
	@JoinColumn(name = "id_lugarespaco", nullable = false, insertable = true, updatable = false)
	private LugarEspaco lugarEspaco;

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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public LugarEspaco getLugarEspaco() {
		return lugarEspaco;
	}

	public void setLugarEspaco(LugarEspaco lugarEspaco) {
		this.lugarEspaco = lugarEspaco;
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

	public BigDecimal getValorPalestrante() {
		return valorPalestrante;
	}

	public void setValorPalestrante(BigDecimal valorPalestrante) {
		this.valorPalestrante = valorPalestrante;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof EventoPalestra) {
			final EventoPalestra c = (EventoPalestra) arg0;
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
