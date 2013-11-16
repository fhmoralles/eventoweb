package br.com.eventoweb.spec;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.libutils.jpa.AbstractRepository;

public abstract class AbstractEventoWebRepository<T extends Serializable>
		extends AbstractRepository<T> {

	@PersistenceContext(unitName = "EventoWebPU")
	private EntityManager em;

	protected AbstractEventoWebRepository(
			final Class<? extends T> entityConcreteClass) {
		super(entityConcreteClass);
	}

	@Override
	protected final EntityManager getEntityManager() {
		return em;
	}
	
}
