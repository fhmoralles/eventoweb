package br.com.eventoweb.view.evento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.eventoweb.domain.evento.Financeiro;
import br.com.eventoweb.domain.types.TipoFinanceiro;
import br.com.eventoweb.model.evento.spec.FinanceiroModel;
import br.com.eventoweb.view.evento.filter.FinanceiroFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class FinanceiroUI extends
		AbstractCRUD<Financeiro, FinanceiroFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5371442897850511521L;

	// private static final Logger LOG = Logger.getLogger(PalestraUI.class);

	private static final String FINANCEIRO_PESQUISAR = "/forms/eventos/financeiroPesquisar.xhtml";
	private static final String FINANCEIRO_CADASTRAR = "/forms/eventos/financeiroCadastrar.xhtml";

	/* Modelo */
	private List<Financeiro> registrosPrevistos;
	private List<Financeiro> registrosRealizados;

	private String tipoLancamento;

	@Inject
	private FinanceiroModel financeiroModel;

	public FinanceiroUI() {
		super(new FinanceiroFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteImpl(Financeiro bean) throws Exception {
		financeiroModel.delete(this.getBean());
	}

	@Override
	protected String getMsgDelete() {
		if (this.getBean() != null) {
			return this.getBean().getDescricao();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected String getActionCreate() {
		return FINANCEIRO_CADASTRAR;
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
		return FINANCEIRO_PESQUISAR;
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
	protected boolean isValidBean(Financeiro bean) {
		return true;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Financeiro newInstance() {
		Financeiro financeiro = new Financeiro();
		financeiro.setEvento(this.getFilter().getEvento());

		return financeiro;
	}

	@Override
	protected void saveImpl(Financeiro bean) throws Exception {

		bean.setTipoFinanceiro(TipoFinanceiro.REALIZADO);
		bean.setDataRealizado(Calendar.getInstance().getTime());
		bean.setEvento(this.getFilter().getEvento());
		if (this.getTipoLancamento().equals("D")) {
			bean.setValorFinanceiro(bean.getValorFinanceiro().negate());
		}

		financeiroModel.update(bean);
	}

	@Override
	protected List<Financeiro> searchImpl(FinanceiroFilter filter) {

		/* Preenche os registros Previstos e Realizados */
		this.setRegistrosPrevistos(financeiroModel
				.registrosPrevistosFinanceiroEvento(this.getFilter()
						.getEvento()));
		this.setRegistrosRealizados(financeiroModel
				.registrosRealizadosFinanceiroEvento(this.getFilter()
						.getEvento()));

		List<Financeiro> aux = new ArrayList<Financeiro>();
		aux.addAll(this.getRegistrosPrevistos());
		aux.addAll(this.getRegistrosRealizados());

		return aux;
	}

	public List<Financeiro> getRegistrosPrevistos() {
		return registrosPrevistos;
	}

	public void setRegistrosPrevistos(List<Financeiro> registrosPrevistos) {
		this.registrosPrevistos = registrosPrevistos;
	}

	public List<Financeiro> getRegistrosRealizados() {
		return registrosRealizados;
	}

	public void setRegistrosRealizados(List<Financeiro> registrosRealizados) {
		this.registrosRealizados = registrosRealizados;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	protected BigDecimal getTotalRegistrosPrevistos() {

		BigDecimal totalRegistros = new BigDecimal(0);

		if (this.getRegistrosPrevistos() != null) {
			for (Financeiro financeiro : this.getRegistrosPrevistos()) {
				totalRegistros = totalRegistros.add(financeiro
						.getValorFinanceiro());
			}
		}

		return totalRegistros;
	}

	protected BigDecimal getTotalRegistrosRealizados() {

		BigDecimal totalRegistros = new BigDecimal(0);

		if (this.getRegistrosRealizados() != null) {
			for (Financeiro financeiro : this.getRegistrosRealizados()) {
				totalRegistros = totalRegistros.add(financeiro
						.getValorFinanceiro());
			}
		}

		return totalRegistros;
	}

	protected BigDecimal getTotalRegistrosProvisao() {

		return this.getTotalRegistrosRealizados().add(
				this.getTotalRegistrosPrevistos());
	}

}
