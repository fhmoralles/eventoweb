package br.com.eventoweb.domain.parametros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "parametro")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Parametro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1366077105133175185L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "caminhoarquivosubmissao", nullable = false, insertable = true, updatable = false, length = 255, unique = true)
	private String caminhoArquivoSubmissao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaminhoArquivoSubmissao() {
		return caminhoArquivoSubmissao;
	}

	public void setCaminhoArquivoSubmissao(String caminhoArquivoSubmissao) {
		this.caminhoArquivoSubmissao = caminhoArquivoSubmissao;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Parametro) {
			final Parametro c = (Parametro) arg0;
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
