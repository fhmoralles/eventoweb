package br.com.eventoweb.view.main;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import br.com.eventoweb.domain.evento.Atividade;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Local;
import br.com.eventoweb.model.evento.spec.AtividadeModel;
import br.com.eventoweb.model.evento.spec.EventoModel;
import br.com.eventoweb.model.evento.spec.LocalModel;
import br.com.webutils.MessageUtil;

@Named
@RequestScoped
public class EventoPaginaUI {

	@Inject
	private EventoModel eventoModel;
	@Inject
	private LocalModel localModel;
	@Inject
	private AtividadeModel atividadeModel;

	private Evento evento;

	@PostConstruct
	public void buscarEvento() {

		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		String strIdEvento = httpServletRequest.getParameter("evento");

		if (strIdEvento != null && eventoModel != null) {
			try {
				this.evento = eventoModel.retrieve(Long.parseLong(strIdEvento));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getNome() {
		return evento.getNome();
	}

	public Evento getEvento() {
		return evento;
	}

	public String getLocalizacao() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(this.getEvento().getDataInicio());

		List<Local> locais = localModel.locaisEvento(this.getEvento());
		Local local = null;
		String localizacao = StringUtils.EMPTY;
		String cidade = StringUtils.EMPTY;
		String estado = StringUtils.EMPTY;
		if (locais.size() > 0) {
			local = locais.get(0);
			localizacao = local.getParticipante().getCadastro().getFantasia();
			cidade = local.getParticipante().getCadastro().getCidade();
			estado = local.getParticipante().getCadastro().getEstado()
					.toString();
		}

		String msg = MessageUtil.getMessage("search.localizacao.msg", data,
				localizacao, cidade, estado);
		return msg;
	}

	public String getLinkInscricao() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath()
				+ "/eventoInscricao.xhtml?evento=" + evento.getId();

	}

	public String getLinkSubmissao() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath()
				+ "/eventoSubmissao.xhtml?evento=" + evento.getId();

	}

	public List<Atividade> palestrantes() {
		return atividadeModel.palestrasEvento(this.getEvento());
	}

	public List<Atividade> cursos() {
		return null;
	}

	public List<Atividade> minicursos() {
		return null;
	}
	
	public List<Atividade> workshops() {
		return null;
	}
	
}
