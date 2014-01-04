package br.com.eventoweb.model.evento.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Comite;
import br.com.eventoweb.domain.evento.ComiteMembro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.evento.SubmissaoArquivo;
import br.com.eventoweb.domain.evento.SubmissaoAvaliacao;
import br.com.eventoweb.domain.exceptions.ComiteSemMembrosException;
import br.com.eventoweb.domain.exceptions.ComiteSemPresidenteException;
import br.com.eventoweb.domain.exceptions.TemaSemComiteException;
import br.com.eventoweb.domain.parametros.Parametro;
import br.com.eventoweb.domain.types.ArquivoSubmissao;
import br.com.eventoweb.domain.types.StatusSubmissao;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.evento.spec.SubmissaoModel;
import br.com.eventoweb.repository.evento.spec.ComiteMembroRepository;
import br.com.eventoweb.repository.evento.spec.ComiteRepository;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;
import br.com.eventoweb.repository.evento.spec.SubmissaoRepository;
import br.com.eventoweb.repository.parametros.spec.ParametroRepository;
import br.com.libutils.email.EmailHelper;
import br.com.libutils.email.EmailHelper.TipoEmail;

@Stateless(name = "submissaoModel")
public class SubmissaoModelIml implements SubmissaoModel {

	@EJB
	private SubmissaoRepository submissaoRepository;

	@EJB
	private ParticipanteRepository participanteRepository;

	@EJB
	private ParametroRepository parametroRepository;

	@EJB
	private ComiteRepository comiteRepository;

	@EJB
	private ComiteMembroRepository comiteMembroRepository;

	@Override
	public void create(Submissao c) throws Exception {
		submissaoRepository.create(c);
	}

	@Override
	public Submissao retrieve(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Submissao update(Submissao c) throws Exception {

		Submissao submissao = submissaoRepository.update(c);

		/* Enviar e-mail para quem submeteu com o Status */
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("<table style=\"width: 100%; text-align: center;\">");
		mensagem.append("	<tr>");
		mensagem.append("		<td style=\"padding-bottom: 20px;\">");
		mensagem.append("			<h1>");
		mensagem.append("				Evento: ");
		mensagem.append("				<font color=\"#0173B2\">"
				+ submissao.getTema().getEvento()
						.getNome() + "</font>");
		mensagem.append("			</h1>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h4>Aviso de Avaliação de Artigo</h4>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h2>");
		mensagem.append("				<font color=\"#1C7575\">");
		mensagem.append("					Tema: "
				+ submissao.getTema().getDescricao());
		mensagem.append("				</font>");
		mensagem.append("			</h2>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h2>");
		mensagem.append("				<font color=\"#1C7575\">");
		mensagem.append("					Status: " + submissao.getStatus());
		mensagem.append("				</font>");
		mensagem.append("			</h2>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("</table>");

		String destinatario = submissao.getParticipante().getCadastro().getUsuario().getEmail();

		if (submissao.getStatus() != StatusSubmissao.AGUARDANDO) {

			/* Envia para quem submeteu */
			EmailHelper.enviarEmail(destinatario, "Avisto: Submissão de Artigo",
					mensagem.toString(), TipoEmail.HTML, null);

		}
		
		return submissao;
	}

	@Override
	public void delete(Submissao c) throws Exception {

		/* Necessario para trazer a entidade para contexto gerenciado */
		c = submissaoRepository.update(c);

		/* Excluir */
		submissaoRepository.delete(c);
		participanteRepository.delete(c.getParticipante());
	}

	@Override
	public void refresh(Submissao c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Submissao> submissoesEvento(Evento e, Cadastro c) {

		/*
		 * As submissões serão recuperadas em 2 momentos: 1. Se o Cadastro for
		 * do Criado do Evento, pode ver todas as submissões 2. Se o Cadastro
		 * for do Comite para o Tema enviado, pode ver as sumissções para o Tema
		 * cujo o comite é presente
		 */
		Boolean criador = false;
		Boolean comite = false;
		for (Participante participante : e.getParticipantes()) {
			if (participante.getCadastro().equals(c)
					&& participante.getTipoParticipante() == TipoParticipante.CRIADOR) {
				criador = true;
			} else if (participante.getCadastro().equals(c)
					&& participante.getTipoParticipante() == TipoParticipante.COMITE) {
				comite = true;
			}
		}

		if (criador) {
			/*
			 * O Criador do Envento pode recuperar todos as submissões feitas
			 * para o evento
			 */
			return submissaoRepository.submissoesCriadorEvento(e);

		} else if (comite) {

			/*
			 * Não é o Criador do evento, então precisa chegar se é Presidente
			 * do Comite para o Tema
			 */
			return submissaoRepository.submissoesComiteEvento(e, c);
		}

		return new ArrayList<Submissao>();
	}

	@Override
	public Submissao update(Submissao submissao, List<ArquivoSubmissao> arquivos)
			throws Exception {

		Parametro parametro = parametroRepository.retrieve(1L);
		List<SubmissaoArquivo> submissaoArquivos = new ArrayList<SubmissaoArquivo>();

		File arquivoTo = null;
		StringBuilder caminhoArquivo = new StringBuilder();
		SubmissaoArquivo submissaoArquivo = null;
		BufferedOutputStream bufferedOutputStream = null;

		for (ArquivoSubmissao arquivoFrom : arquivos) {

			caminhoArquivo.delete(0, caminhoArquivo.length());
			caminhoArquivo.append(parametro.getCaminhoArquivoSubmissao());
			arquivoTo = new File(caminhoArquivo.toString());

			caminhoArquivo.append("/")
					.append(submissao.getParticipante().getCadastro().getId())
					.append("-").append(arquivoFrom.getNomeArquivo());
			arquivoTo = new File(caminhoArquivo.toString());
			if (!arquivoTo.exists()) {
				arquivoTo.createNewFile();
			}

			bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(arquivoTo));
			bufferedOutputStream.write(arquivoFrom.getByteArquivo());

			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			submissaoArquivo = new SubmissaoArquivo();
			submissaoArquivo.setArquivo(arquivoTo.getAbsolutePath());
			submissaoArquivo.setTamanho(arquivoTo.length());
			submissaoArquivo.setSubmissao(submissao);

			submissaoArquivos.add(submissaoArquivo);
		}

		Participante participante = submissao.getParticipante();
		participante = participanteRepository.update(participante);
		submissao.setParticipante(participante);

		submissao.setArquivos(submissaoArquivos);

		submissao = submissaoRepository.update(submissao);

		/* Envia e-mail */
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("<table style=\"width: 100%; text-align: center;\">");
		mensagem.append("	<tr>");
		mensagem.append("		<td style=\"padding-bottom: 20px;\">");
		mensagem.append("			<h1>");
		mensagem.append("				Evento: ");
		mensagem.append("				<font color=\"#0173B2\">"
				+ submissao.getTema().getEvento().getNome() + "</font>");
		mensagem.append("			</h1>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h4>Aviso de Submissão de Artigo</h4>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h2>");
		mensagem.append("				<font color=\"#1C7575\">");
		mensagem.append("					Tema: " + submissao.getTema().getDescricao());
		mensagem.append("				</font>");
		mensagem.append("			</h2>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("	<tr>");
		mensagem.append("		<td>");
		mensagem.append("			<h4>.: Eviado por "
				+ submissao.getParticipante().getCadastro().getFantasia()
				+ "</h4>");
		mensagem.append("		</td>");
		mensagem.append("	</tr>");
		mensagem.append("</table>");

		List<Comite> comitesTema = comiteRepository.comitesTema(submissao
				.getTema());

		if (comitesTema != null && comitesTema.size() > 0) {
			Comite comiteTema = comitesTema.get(0);
			List<ComiteMembro> comiteMembros = comiteMembroRepository
					.membrosComite(comiteTema);

			if (comiteMembros != null && comiteMembros.size() > 0) {

				ComiteMembro comiteMembro = null;
				for (ComiteMembro aux : comiteMembros) {

					if (aux.getTipoComiteMembro().equals(
							TipoComiteMembro.PRESIDENTE)) {
						comiteMembro = aux;
						break;
					}
				}

				if (comiteMembro != null) {
					EmailHelper.enviarEmail(comiteMembro.getParticipante()
							.getCadastro().getUsuario().getEmail(),
							"Avisto: Submissão de Artigo", mensagem.toString(),
							TipoEmail.HTML, null);
				} else {
					throw new ComiteSemPresidenteException();
				}

			} else {
				throw new ComiteSemMembrosException();
			}
		} else {
			throw new TemaSemComiteException();
		}

		return submissao;
	}

}
