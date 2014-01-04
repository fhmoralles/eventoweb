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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "submissaoarquivo")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class SubmissaoArquivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436814585065625226L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_submissao", nullable = false, insertable = true, updatable = false)
	private Submissao submissao;

	@Column(name = "arquivo", nullable = true, insertable = true, updatable = true, length = 4096)
	private String arquivo;

	@Column(name = "tamanho", nullable = true, insertable = true, updatable = true, length = 4096)
	private Long tamanho;

	@Transient
	private String arquivoRelativo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Submissao getSubmissao() {
		return submissao;
	}

	public void setSubmissao(Submissao submissao) {
		this.submissao = submissao;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public String getArquivoRelativo() {
		
		int index = this.getArquivo().lastIndexOf("/");
		if(index < 0) {
			index = this.getArquivo().lastIndexOf("\\");
		}
		
		if(index < 0) {
			return null;
		}
		
		return this.getArquivo().substring(index+1);
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof SubmissaoArquivo) {
			final SubmissaoArquivo c = (SubmissaoArquivo) arg0;
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
