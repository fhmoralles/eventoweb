package br.com.eventoweb.domain.cadastro;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.eventoweb.domain.types.Estado;
import br.com.eventoweb.domain.types.TipoDocumento;

@Entity
@Table(name = "lugar")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Lugar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6479863046073660676L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "razaosocial", nullable = false, insertable = true, updatable = true, length = 255)
	private String razaoSocial;

	@Column(name = "fantasia", nullable = false, insertable = true, updatable = true, length = 255)
	private String fantasia;

	@Column(name = "tipodocumento", nullable = false, insertable = true, updatable = false)
	@Enumerated(EnumType.STRING)
	private TipoDocumento tipoDocumento;

	@Column(name = "documento", nullable = false, insertable = true, updatable = false, unique = true, length = 20)
	private String documento;

	@Column(name = "cep", nullable = false, insertable = true, updatable = true, length = 9)
	private String cep;

	@Column(name = "endereco", nullable = false, insertable = true, updatable = true, length = 255)
	private String endereco;

	@Column(name = "numero", nullable = false, insertable = true, updatable = true, length = 10)
	private String numero;

	@Column(name = "complemento", nullable = false, insertable = true, updatable = true, length = 100)
	private String complemento;

	@Column(name = "bairro", nullable = false, insertable = true, updatable = true, length = 50)
	private String bairro;

	@Column(name = "cidade", nullable = false, insertable = true, updatable = true, length = 100)
	private String cidade;

	@Column(name = "estado", nullable = false, insertable = true, updatable = true, length = 2)
	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Column(name = "telefone", nullable = true, insertable = true, updatable = true, length = 20)
	private String telefone;

	@Column(name = "fax", nullable = true, insertable = true, updatable = true, length = 20)
	private String fax;

	@OneToMany(mappedBy = "lugar")
	private List<LugarEspaco> lugaresEspaco;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public List<LugarEspaco> getLugaresEspaco() {
		return lugaresEspaco;
	}

	public void setLugaresEspaco(List<LugarEspaco> lugaresEspaco) {
		this.lugaresEspaco = lugaresEspaco;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Lugar) {
			final Lugar c = (Lugar) arg0;
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
