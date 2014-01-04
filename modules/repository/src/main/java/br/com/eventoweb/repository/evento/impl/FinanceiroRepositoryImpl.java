package br.com.eventoweb.repository.evento.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Financeiro;
import br.com.eventoweb.domain.types.TipoFinanceiro;
import br.com.eventoweb.repository.evento.spec.FinanceiroRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "financeiroRepository")
public class FinanceiroRepositoryImpl extends
		AbstractEventoWebRepository<Financeiro> implements
		FinanceiroRepository {

	public FinanceiroRepositoryImpl() {
		super(Financeiro.class);
	}

	@Override
	public List<Financeiro> registrosPrevistosFinanceiroEvento(Evento evento) {
		return registrosFinanceiro(evento, TipoFinanceiro.PREVISTO);
	}

	@Override
	public List<Financeiro> registrosRealizadosFinanceiroEvento(
			Evento evento) {
		return registrosFinanceiro(evento, TipoFinanceiro.REALIZADO);
	}
	
	private List<Financeiro> registrosFinanceiro(Evento evento, TipoFinanceiro tipoFinanceiro) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Financeiro> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Financeiro> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, evento); 

		final Path<TipoFinanceiro> pathTipoFinanceiro = root.get("tipoFinanceiro");
		final Predicate predicateTipoFinanceiro = criteriaBuilder.equal(pathTipoFinanceiro, tipoFinanceiro); 
		
		criteriaQuery.where(predicateEvento, predicateTipoFinanceiro);
		final Path<String> pathData;
		if(tipoFinanceiro == TipoFinanceiro.PREVISTO) {
			pathData = root.get("dataPrevisto");
		} else {
			pathData = root.get("dataRealizado");
		}
		criteriaQuery.orderBy(criteriaBuilder.desc(pathData));
		
		final TypedQuery<Financeiro> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}

}
