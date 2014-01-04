package br.com.eventoweb.repository.evento.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.repository.evento.spec.EventoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "eventoRepository")
public class EventoRepositoryImpl extends
		AbstractEventoWebRepository<Evento> implements
		EventoRepository {

	public EventoRepositoryImpl() {
		super(Evento.class);
	}

	@Override
	public List<Evento> eventosCadastro(Cadastro c) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Evento> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Evento> root = createRoot(criteriaQuery);
	
		final Join<Evento, Participante> join = root.join("participantes");
		
		criteriaQuery.where(criteriaBuilder.equal(join.get("cadastro"), c));
		
		criteriaQuery.select(root).distinct(true);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dataInicio")));
		
		return this.retrieveByCriteria(criteriaQuery);
		
	}

	@Override
	public List<Evento> eventosPorNome(String nome) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Evento> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Evento> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados
		 * Filtro por codigo */
		final Path<String> pathNome = root.get("nome");
		final Predicate predicateNome = criteriaBuilder.like(pathNome, nome);
		criteriaQuery.where(predicateNome);
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dataInicio")));
		
		return this.retrieveByCriteria(criteriaQuery);
	}

}
