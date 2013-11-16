package br.com.eventoweb.model.parametros.impl;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.eventoweb.domain.cadastro.Participante;
import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.model.parametros.spec.UsuarioModel;
import br.com.eventoweb.repository.cadastro.spec.ParticipanteRepository;
import br.com.eventoweb.repository.parametros.spec.UsuarioRepository;
import br.com.libutils.email.EmailHelper;
import br.com.libutils.email.EmailHelper.TipoEmail;
import br.com.libutils.exception.PasswordConfirmationException;
import br.com.libutils.validation.MD5Digest;

@Stateless(name = "usuarioModel")
public class UsuarioModelImpl implements UsuarioModel {

	private static final Logger LOG = Logger.getLogger(UsuarioModelImpl.class);

	@EJB
	private UsuarioRepository usuarioRepository;

	@EJB
	private ParticipanteRepository participanteRepository;
	
	@Resource
	private SessionContext sessionContext;

	@Override
	public void create(Usuario c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario retrieve(Long id) {
		return usuarioRepository.retrieve(id);
	}

	@Override
	public Usuario update(Usuario c) throws Exception {

		Participante p = null;

		/* Persistema participante */
		p = participanteRepository.update(c.getParticipante());
		c.setParticipante(p);

		/* Persiste usuário */
		c = usuarioRepository.update(c);

		return c;
	}

	@Override
	public Usuario update(Usuario u, String url) throws Exception {
		
		u = this.update(u);
		
		/* Envia e-mail */
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("<table style=\"width: 100%; text-align: center;\">");
		mensagem.append("	<tr>");
		mensagem.append("		<td style=\"padding-bottom: 20px;\">");
		mensagem.append("			<h1>");
		mensagem.append("				Obrigado por se cadastrar no");
		mensagem.append("				<font color=\"#0173B2\">EventoWeb</font>");
		mensagem.append("			</h1>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h4>Para confirmar o cadastro, clique no link abaixo</h4>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h2>");
		mensagem.append("				<font color=\"#1C7575\">");
		mensagem.append("					<a href=\"" + url + "forms/cadastros/cadastroConfirmacao.xhtml?id=" + u.getId() + "&token=" + u.getSenha() + "\">Confirmar Cadastro</a>");
		mensagem.append("				</font>");
		mensagem.append("			</h2>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h4>.: para se logar no sistema, é necessário confirmar através do link que consta no e-mail</h4>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("</table>");
		
		UsuarioModel usuarioModel = sessionContext.getBusinessObject(UsuarioModel.class);
		usuarioModel.enviarEmail(u.getEmail(), "EventoWeb: Confirmação", mensagem.toString());
		
		return u;
	}
	
	@Override
	public void delete(Usuario c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Usuario c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario login(String email, String senha)
			throws PasswordConfirmationException {

		// Recupera o operador
		Usuario usuario = usuarioRepository.localizarPorEmail(email);

		if (usuario != null) {

			LOG.info("Usuario encontrado. Verificando a Senha");
			MD5Digest md5 = MD5Digest.getInstance();
			String senhaMd5 = md5.generateDigest(senha);

			if (senhaMd5.equals(usuario.getSenha())) {
				return usuario;
			}

		}

		LOG.info("Usuario não encontrado ou Senha Inválida!!");

		throw new PasswordConfirmationException();
	}

	@Override
	public void enviarEmail(String email, String assunto, String mensagem) {
		LOG.info("Enviado email: " + mensagem);
		EmailHelper.enviarEmail(email, assunto, mensagem, TipoEmail.HTML, null);
	}

}
