package br.com.eventoweb.model.evento.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.SubmissaoAvaliacao;
import br.com.eventoweb.domain.types.StatusSubmissao;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.model.evento.spec.SubmissaoAvaliacaoModel;
import br.com.eventoweb.repository.evento.spec.ComiteRepository;
import br.com.eventoweb.repository.evento.spec.SubmissaoAvaliacaoRepository;
import br.com.libutils.email.EmailHelper;
import br.com.libutils.email.EmailHelper.TipoEmail;

@Stateless(name = "submissaoAvaliacaoModel")
public class SubmissaoAvaliacaoModelIml implements SubmissaoAvaliacaoModel {

	@EJB
	private SubmissaoAvaliacaoRepository submissaoAvaliacaoRepository;
	@EJB
	private ComiteRepository comiteRepository;

	@Override
	public void create(SubmissaoAvaliacao s) throws Exception {
		submissaoAvaliacaoRepository.create(s);
	}

	@Override
	public SubmissaoAvaliacao retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubmissaoAvaliacao update(SubmissaoAvaliacao s) throws Exception {

		SubmissaoAvaliacao submissaoAvaliacao = submissaoAvaliacaoRepository
				.update(s);

		/* Envia e-mail */
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("<table style=\"width: 100%; text-align: center;\">");
		mensagem.append("	<tr>");
		mensagem.append("		<td style=\"padding-bottom: 20px;\">");
		mensagem.append("			<h1>");
		mensagem.append("				Evento: ");
		mensagem.append("				<font color=\"#0173B2\">"
				+ submissaoAvaliacao.getSubmissao().getTema().getEvento()
						.getNome() + "</font>");
		mensagem.append("			</h1>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h4>Aviso de Submissão de Artigo para Avaliação</h4>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h2>");
		mensagem.append("				<font color=\"#1C7575\">");
		mensagem.append("					Tema: "
				+ submissaoAvaliacao.getSubmissao().getTema().getDescricao());
		mensagem.append("				</font>");
		mensagem.append("			</h2>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h4>.: Eviado por "
				+ submissaoAvaliacao.getSubmissao().getParticipante()
						.getCadastro().getRazaoSocial() + "</h4>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		if (submissaoAvaliacao.getStatus() != StatusSubmissao.AGUARDANDO) {
			mensagem.append("	<tr>");
			mensagem.append("		<td>");
			mensagem.append("			<h2>");
			mensagem.append("				<font color=\"#1C7575\">");
			mensagem.append("					Status: " + submissaoAvaliacao.getStatus());
			mensagem.append("				</font>");
			mensagem.append("			</h2>");
			mensagem.append("		</td>");
			mensagem.append("	</tr>");
			mensagem.append("	<tr>");
			mensagem.append("		<td>");
			mensagem.append("			<h4>.: Atualizado por "
					+ submissaoAvaliacao.getComiteMembro().getParticipante()
							.getCadastro().getRazaoSocial() + "</h4>");
			mensagem.append("		</td>");
			mensagem.append("	</tr>");
		}
		mensagem.append("</table>");

		String destinatario = null;

		if (submissaoAvaliacao.getStatus() == StatusSubmissao.AGUARDANDO) {

			/* Envia para o Avaliador */
			destinatario = submissaoAvaliacao.getComiteMembro()
					.getParticipante().getCadastro().getUsuario().getEmail();
		} else {

			/* Enviar para o Presidente */
			List<Comite> comites = comiteRepository
					.comitesTema(submissaoAvaliacao.getSubmissao().getTema());
			Comite comite = comites.get(0);

			for (ComiteMembro comiteMembro : comite.getMembros()) {
				if (comiteMembro.getTipoComiteMembro() == TipoComiteMembro.PRESIDENTE) {
					destinatario = comiteMembro.getParticipante().getCadastro()
							.getUsuario().getEmail();
				}
			}
		}

		EmailHelper.enviarEmail(destinatario, "Avisto: Submissão de Artigo",
				mensagem.toString(), TipoEmail.HTML, null);

		return submissaoAvaliacao;
	}

	@Override
	public void delete(SubmissaoAvaliacao s) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		s = submissaoAvaliacaoRepository.update(s);

		/* Excluir */
		submissaoAvaliacaoRepository.delete(s);
	}

	@Override
	public void refresh(SubmissaoAvaliacao s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SubmissaoAvaliacao> avaliacoesSubmissao(Submissao submissao) {
		return submissaoAvaliacaoRepository.avaliacoesSubmissao(submissao);
	}

}
