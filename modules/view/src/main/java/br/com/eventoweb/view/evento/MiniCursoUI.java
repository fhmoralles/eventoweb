package br.com.eventoweb.view.evento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.cadastro.Espaco;
import br.com.eventoweb.domain.evento.Atividade;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.evento.Tema;
import br.com.eventoweb.domain.types.TipoAtividade;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.model.cadastro.spec.EspacoModel;
import br.com.eventoweb.model.evento.spec.AtividadeModel;
import br.com.eventoweb.model.evento.spec.ParticipanteModel;
import br.com.eventoweb.model.evento.spec.TemaModel;
import br.com.eventoweb.view.evento.filter.AtividadeFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class MiniCursoUI extends AbstractCRUD<Atividade, AtividadeFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5371442897850511521L;

	//private static final Logger LOG = Logger.getLogger(MiniCursoUI.class);
	
	private static final String MINICURSO_PESQUISAR = "/forms/eventos/miniCursoPesquisar.xhtml";
	private static final String MINICURSO_CADASTRAR = "/forms/eventos/miniCursoCadastrar.xhtml";

	@Inject
	private AtividadeModel atividadeModel;
	@Inject
	private EspacoModel espacoModel;
	@Inject
	private ParticipanteModel participanteModel;
	@Inject
	private TemaModel temaModel;
	
	public MiniCursoUI() {
		super(new AtividadeFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Atividade bean) throws Exception {
		atividadeModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if(this.getBean() != null) {
			return this.getBean().getTitulo();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return MINICURSO_CADASTRAR;
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
		return MINICURSO_PESQUISAR;
	}

	@Override
	public boolean isDeletable() {
		return true;
	}

	@Override
	public boolean isEditable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInsertable() {
		return true;
	}

	@Override
	public boolean isSearchable() {
		return false;
	}

	@Override
	protected boolean isSearchOnPrepare() {
		return true;
	}

	@Override
	protected boolean isValidBean(Atividade bean) {
		return true;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Atividade newInstance() {
		Atividade minicurso = new Atividade();
		minicurso.setParticipante(new Participante());
		minicurso.getParticipante().setEvento(this.getFilter().getEvento());
		minicurso.setTipoAtividade(TipoAtividade.MINICURSO);
		minicurso.getParticipante().setTipoParticipante(TipoParticipante.INSTRUTOR);

		return minicurso;
	}

	@Override
	protected void saveImpl(Atividade bean) throws Exception {
		atividadeModel.update(bean);
	}

	@Override
	protected List<Atividade> searchImpl(AtividadeFilter filter) {
		return atividadeModel.minicursosEvento(filter.getEvento());
	}

	public List<Espaco> getParticipantesEspacos() {

		List<Participante> locais = participanteModel.locaisEvento(this.getFilter().getEvento());
		List<Espaco> espacos = new ArrayList<Espaco>();

		for (Participante local : locais) {
			espacos.addAll(espacoModel.espacosParticipante(local.getCadastro()));
		}

		return espacos;
	}

	public List<Tema> getTemas() {
		return temaModel.temas(this.getFilter().getEvento());
	}
	
	public List<String> getDatasAtividade() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<String> datas = new ArrayList<String>();
		Calendar auxDataInicio = Calendar.getInstance();
		auxDataInicio.setTime(this.getFilter().getEvento().getDataInicio());
		this.getBean().setDataAtividade(this.getFilter().getEvento().getDataInicio());
		
		Calendar auxDataFim = null;
		
		if(this.getFilter().getEvento().getDataFim() != null) {
			auxDataFim = Calendar.getInstance();
			auxDataFim.setTime(this.getFilter().getEvento().getDataFim());
		}
		
		
		if(auxDataFim != null && auxDataInicio.before(auxDataFim)) {
			
			do {
				datas.add(sdf.format(auxDataInicio.getTime()));
				auxDataInicio.add(Calendar.DATE, 1);
			}while(!auxDataInicio.after(auxDataFim));
			
		} else {
			datas.add(sdf.format(auxDataInicio.getTime()));
		}
		
		return datas;
	}
	
}
