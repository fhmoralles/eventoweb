package br.com.eventoweb.view.main.converter;

import java.lang.reflect.Method;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

public class AbstractEntityConverter<T> implements Converter {

	private final Class<? extends T> entityClass;

	protected AbstractEntityConverter(final Class<? extends T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String value) {
		return StringUtils.isNotBlank(value) ? component.getAttributes().get(
				value) : StringUtils.EMPTY;
	}

	@Override
	public String getAsString(final FacesContext context,
			final UIComponent component, final Object object) {
		
		if (this.entityClass.isInstance(object)) {

			try {

				final Method method = entityClass.getMethod("getId", null);

				if (method == null) {
					return null;
				}

				final Object retorno = method.invoke(object, null);

				if (retorno == null) {
					return null;
				}

				component.getAttributes().put(retorno.toString(), object);

				return retorno.toString();

			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		return StringUtils.EMPTY;
	}

}
