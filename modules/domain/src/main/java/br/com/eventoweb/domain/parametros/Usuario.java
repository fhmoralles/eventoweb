package br.com.eventoweb.domain.parametros;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.eventoweb.domain.cadastro.Cadastro;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3218473841977454102L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_cadastro", nullable = false, insertable = true, updatable = false)
	private Cadastro cadastro;

	@Column(name = "email", nullable = false, insertable = true, updatable = false, length = 255, unique = true)
	private String email;

	@Column(name = "senha", nullable = false, insertable = true, updatable = true, length = 32)
	private String senha;

	@Column(name = "organizador", nullable = false, insertable = true, updatable = true)
	private Boolean organizador;

	@Column(name = "ativo", nullable = false, insertable = true, updatable = true)
	private Boolean ativo;
	
	@Column(name = "datacadastro", nullable = false, insertable = true, updatable = true)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "datavalidade", nullable = false, insertable = true, updatable = true)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataValidade;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Boolean organizador) {
		this.organizador = organizador;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Usuario) {
			final Usuario c = (Usuario) arg0;
			return new EqualsBuilder().append(
					this.getCadastro().getDocumento(),
					c.getCadastro().getDocumento()).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(
				this.getCadastro().getDocumento()).toHashCode();
	}

}
