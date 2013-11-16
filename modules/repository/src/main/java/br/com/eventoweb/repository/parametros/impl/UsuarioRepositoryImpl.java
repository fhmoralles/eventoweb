package br.com.eventoweb.repository.parametros.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.eventoweb.domain.parametros.Usuario;
import br.com.eventoweb.repository.parametros.spec.UsuarioRepository;
import br.com.eventoweb.spec.AbstractEventoWebRepository;

@Stateless(name = "usuarioRepository")
public class UsuarioRepositoryImpl extends
		AbstractEventoWebRepository<Usuario> implements UsuarioRepository {

	protected UsuarioRepositoryImpl() {
		super(Usuario.class);
	}

	@Override
	public Usuario localizarPorEmail(String email) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Usuario> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Usuario> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados
		 * Filtro por codigo */
		final Path<String> pathEmail = root.get("email");
		final Predicate predicateEmail = criteriaBuilder.equal(pathEmail, email);
		criteriaQuery.where(predicateEmail);
		
		final TypedQuery<Usuario> typedQuery = em.createQuery(criteriaQuery);
		
		return ( (typedQuery.getResultList().size()) == 0 ? null : typedQuery.getResultList().get(0) );

	}

}
