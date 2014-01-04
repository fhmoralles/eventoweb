package br.com.eventoweb.view.cadastro;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.domain.types.Estado;
import br.com.eventoweb.domain.types.TipoDocumento;
import br.com.eventoweb.model.cadastro.spec.TipoDocumentoModel;
import br.com.eventoweb.model.parametros.spec.UsuarioModel;
import br.com.eventoweb.view.cadastro.filter.ParticipanteFilter;
import br.com.eventoweb.view.main.constants.MessagesConstants;
import br.com.libutils.exception.EmailValidationException;
import br.com.libutils.validation.MD5Digest;
import br.com.libutils.validation.Validacao;
import br.com.webutils.MessageUtil;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class CadastroUI extends AbstractCRUD<Cadastro, ParticipanteFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9132217812869246215L;

	private static final Logger LOG = Logger.getLogger(CadastroUI.class);

	private static final String CONCLUSAO_PARTICIPANTE = "/forms/cadastros/cadastroConclusao.xhtml";
	private static final String CADASTRO_PARTICIPANTE = "/forms/cadastros/cadastroParticipante.xhtml";

	/* Modelo */
	@Inject
	private TipoDocumentoModel tipoDocumentoModel;

	@Inject
	private UsuarioModel usuarioModel;

	private String documentoPattern;
	private String email;
	private String senha;
	private String senhaConf;
	private Boolean aceite;

	private String emailConf;
	private Boolean confirmado;

	@Override
	protected void cleanUpImpl() {
		this.setEmail(StringUtils.EMPTY);
		this.setSenha(StringUtils.EMPTY);
		this.setSenhaConf(StringUtils.EMPTY);
		this.setAceite(false);
	}

	@Override
	protected void deleteImpl(Cadastro bean) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getMsgDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionCreate() {

		this.getBean().setTipoDocumento(TipoDocumento.FISICA);
		this.setDocumentoPattern("999.999.999-99");

		return CADASTRO_PARTICIPANTE;
	}

	@Override
	protected String getActionDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionSearch() {
		return CONCLUSAO_PARTICIPANTE;
	}

	@Override
	public boolean isDeletable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEditable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInsertable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSearchable() {
		return true;
	}

	@Override
	protected boolean isSearchOnPrepare() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isValidBean(Cadastro bean) {

		boolean valido = true;

		/* Validação do Documento */
		try {
			if (bean.getTipoDocumento() == TipoDocumento.FISICA) {
				Validacao.validaCPF(bean.getDocumento());
			} else {
				Validacao.validaCNPJ(bean.getDocumento());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			MessageUtil.addComponentErrorMessage(
					"contentEventoForm_inputDocumentoText",
					MessagesConstants.MSG_ERROR_DOCUMENTO_INVALIDO,
					bean.getDocumento());
			bean.setDocumento(StringUtils.EMPTY);
			valido = false;
		}

		/* Validação de Email */
		try {
			Validacao.validaEmail(this.getEmail());
			this.setEmailConf(this.getEmail());
		} catch (EmailValidationException e) {
			LOG.error(e.getMessage(), e);
			MessageUtil
					.addComponentErrorMessage(
							"contentEventoForm_inputEmailText",
							MessagesConstants.MSG_ERROR_EMAIL_INVALIDO,
							this.getEmail());
			this.setEmail(StringUtils.EMPTY);
			valido = false;
		}

		/* Validação de Senha */
		if (!this.getSenha().equals(this.getSenhaConf())) {
			LOG.info(MessagesConstants.MSG_ERROR_CONF_SENHA_INVALIDA);
			MessageUtil.addComponentErrorMessage(
					"contentEventoForm_inputSenhaText",
					MessagesConstants.MSG_ERROR_CONF_SENHA_INVALIDA);
			this.setSenha(StringUtils.EMPTY);
			this.setSenhaConf(StringUtils.EMPTY);
			valido = false;
		}

		/* Validação de Senha */
		if (this.getAceite() == null || !this.getAceite()) {
			LOG.info(MessagesConstants.MSG_ERROR_ACEITE_INVALIDO);
			MessageUtil.addComponentErrorMessage(
					"contentEventoForm_inputTermos",
					MessagesConstants.MSG_ERROR_ACEITE_INVALIDO);
			this.setAceite(false);
			valido = false;
		}

		return valido;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Cadastro newInstance() {
		return new Cadastro();
	}

	@Override
	protected void saveImpl(Cadastro bean) throws Exception {
		
		MD5Digest md5Digest = MD5Digest.getInstance();
		
		Calendar dataCadastro = Calendar.getInstance();
		Calendar dataValidade = Calendar.getInstance();

		dataCadastro.setTimeInMillis(System.currentTimeMillis());
		dataValidade.setTimeInMillis(System.currentTimeMillis());
		dataValidade.add(Calendar.MONTH, 1);
		
		/* Gerando Usuário para salvar */
		Usuario usuario = new Usuario();
		usuario.setEmail(this.getEmail());
		usuario.setSenha(this.getSenha());
		usuario.setSenha(md5Digest.generateDigest(this.getSenha()));
		usuario.setAtivo(false);
		usuario.setOrganizador(false);
		usuario.setCadastro(bean);
		usuario.setDataCadastro(dataCadastro.getTime());
		usuario.setDataValidade(dataValidade.getTime());
		
		try {
			usuarioModel.update(
					usuario,
					getRequestURL().substring(0,
							getRequestURL().indexOf("forms")));
		} catch (Exception e) {
			LOG.error(e);
			if (e != null && e.getCause() != null && e.getCause().getCause() != null) {
				String errorMessage = e.getCause().getCause().getMessage();
				if (errorMessage != null && errorMessage.contains("uk_participante_documento")) {
					MessageUtil.addGlobalErrorMessage(
							MessagesConstants.MSG_ERROR_DOCUMENTO_EXISTENTE,
							bean.getDocumento());
				} else if (errorMessage != null && errorMessage.contains("uk_usuario_email")) {
					MessageUtil.addGlobalErrorMessage(
							MessagesConstants.MSG_ERROR_EMAIL_EXISTENTE,
							this.getEmail());
				} else {
					MessageUtil.addGlobalErrorMessage(GLOBAL_MSG_ERROR);
				}
			} else {
				MessageUtil.addGlobalErrorMessage(GLOBAL_MSG_ERROR);
			}
			throw e;
		}

	}

	@Override
	protected List<Cadastro> searchImpl(ParticipanteFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TipoDocumento> getTiposDocumento() {
		return tipoDocumentoModel.getTiposDocumento();
	}

	public String getDocumentoPattern() {
		return documentoPattern;
	}

	public void setDocumentoPattern(String documentoPattern) {
		this.documentoPattern = documentoPattern;
	}

	public List<Estado> getEstados() {
		// return estadoModel.getEstados();
		return null;
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

	public String getSenhaConf() {
		return senhaConf;
	}

	public void setSenhaConf(String senhaConf) {
		this.senhaConf = senhaConf;
	}

	public Boolean getAceite() {
		return aceite;
	}

	public void setAceite(Boolean aceite) {
		this.aceite = aceite;
	}

	public String getEmailConf() {
		return emailConf;
	}

	public void setEmailConf(String emailConf) {
		this.emailConf = emailConf;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	/* Validação de Padrao de CPF ou CNPJ de acordo com a Pessoa */
	public void changePattern(AjaxBehaviorEvent event) {

		UISelectOne uiSelectOne = (UISelectOne) event.getSource();
		TipoDocumento tipoDocumento = (TipoDocumento) uiSelectOne.getValue();

		if (tipoDocumento == TipoDocumento.FISICA) {
			this.setDocumentoPattern("999.999.999-99");
		} else {
			this.setDocumentoPattern("99.999.999/9999-99");
		}

	}

	/* Confirmando click do link do Email */
	public boolean getConfirmacao() {

		confirmado = false;

		String id = this.getRequestParameter("id");
		String token = this.getRequestParameter("token");

		if (id != null && token != null) {

			Usuario u = null;
			try {
				u = usuarioModel.retrieve(Long.parseLong(id));
			} catch(Exception e) {
				u = null;
				LOG.error(e.getMessage(), e);
			}

			if (u != null && !u.getAtivo()) {

				if (u.getSenha().equals(token)) {

					/* Ativia Usuário */
					u.setAtivo(true);
					try {
						usuarioModel.update(u);
						confirmado = true;
					} catch (Exception e) {
						LOG.error(e);
					}
				}
			}
		}

		LOG.info("id: " + id + " token: " + token);
		return confirmado;
	}

}
