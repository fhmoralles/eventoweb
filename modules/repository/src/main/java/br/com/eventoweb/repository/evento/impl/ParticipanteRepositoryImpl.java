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
import br.com.eventoweb.domain.evento.Participante;
import br.com.eventoweb.domain.types.TipoParticipante;
import br.com.eventoweb.repository.evento.spec.ParticipanteRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "participanteRepository")
public class ParticipanteRepositoryImpl extends
		AbstractEventoWebRepository<Participante> implements
		ParticipanteRepository {

	public ParticipanteRepositoryImpl() {
		super(Participante.class);
	}

	@Override
	public List<Participante> organizadoresEvento(Evento e) {
		return tiposEvento(e, TipoParticipante.ORGANIZADOR);
	}

	@Override
	public List<Participante> realizadoresEvento(Evento e) {
		return tiposEvento(e, TipoParticipante.REALIZADOR);
	}
	
	@Override
	public List<Participante> apoiadoresEvento(Evento e) {
		return tiposEvento(e, TipoParticipante.APOIADOR);
	}

	@Override
	public List<Participante> locaisEvento(Evento e) {
		return tiposEvento(e, TipoParticipante.LOCAL);
	}
	
	private List<Participante> tiposEvento(Evento e, TipoParticipante tipoParticipante) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Participante> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Participante> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e);

		final Path<TipoParticipante> pathTipoParticipante = root.get("tipoParticipante");
		final Predicate predicateTipoParticipante = criteriaBuilder.equal(pathTipoParticipante, tipoParticipante);

		criteriaQuery.where(predicateEvento, predicateTipoParticipante);
		
		final TypedQuery<Participante> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}

}
