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

import br.com.eventoweb.domain.cadastro.Cadastro;
import br.com.eventoweb.domain.evento.Evento;
import br.com.eventoweb.domain.evento.Submissao;
import br.com.eventoweb.domain.types.TipoComiteMembro;
import br.com.eventoweb.repository.evento.spec.SubmissaoRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "submissaoRepository")
public class SubmissaoRepositoryImpl extends
		AbstractEventoWebRepository<Submissao> implements SubmissaoRepository {

	public SubmissaoRepositoryImpl() {
		super(Submissao.class);
	}

	@Override
	public List<Submissao> submissoesCriadorEvento(Evento e) {

		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Submissao> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Submissao> root = createRoot(criteriaQuery);

		/* Repository -> Apenas pesquias as informações no banco de dados */

		/* Filtra por evento */
		final Path<Evento> pathEvento = root.get("tema").get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, e);

		criteriaQuery.where(predicateEvento);

		return this.retrieveByCriteria(criteriaQuery);

	}

	@Override
	public List<Submissao> submissoesComiteEvento(Evento e, Cadastro c) {

		final EntityManager em = getEntityManager();
		StringBuilder sb = new StringBuilder();
		sb.append(
				"Select distinct s " +
				"from " +
				"Submissao s, " +
				"Tema t," +
				"Comite c," +
				"ComiteMembro cm," +
				"Participante p " +
				"where " +
				"s.tema.id = t.id and " +
				"t.id = c.tema.id and " +
				"cm.comite.id = c.id and " +
				"cm.participante.id = p.id and " +
				"((p.cadastro = :cadastro and cm.tipoComiteMembro = :funcaopresidente) or " +
				"(p.cadastro = :cadastro and cm.tipoComiteMembro = :funcaomembro and (select count(sa) from SubmissaoAvaliacao sa where sa.comiteMembro = cm) > 0)) and " +
				"t.evento = :evento " +
				"order by s.tema, s.status");
		TypedQuery<Submissao> tq = em.createQuery(sb.toString(),
				Submissao.class);

		tq.setParameter("cadastro", c);
		tq.setParameter("evento", e);
		tq.setParameter("funcaopresidente", TipoComiteMembro.PRESIDENTE);
		tq.setParameter("funcaomembro", TipoComiteMembro.MEMBRO);

		return tq.getResultList();

		/*
		 * final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		 * final CriteriaQuery<Submissao> criteriaQuery =
		 * createCriteriaQuery(criteriaBuilder); final Root<Submissao> root =
		 * createRoot(criteriaQuery);
		 * 
		 * final Join<Submissao, Tema> joinTema = root.join("tema");
		 * joinTema.get final Join<Tema, Comite> joinComite =
		 * joinTema.join("tema"); //joinComite.join("membros");
		 */

		/* Filtra por evento */
		// final Path<Cadastro> pathCadastro =
		// root.get("tema").get("comite").get("evento");
		// final Predicate predicateEvento = criteriaBuilder.equal(pathEvento,
		// e);

		// final Path<Cadastro> pathCadastro =
		// root.get("participante").get("cadastro");
		// final Predicate predicateCadastro =
		// criteriaBuilder.equal(pathCadastro, c);

		// criteriaQuery.where(predicateEvento, predicateCadastro);

		// return this.retrieveByCriteria(criteriaQuery);

	}

}
