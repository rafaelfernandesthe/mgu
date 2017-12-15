package br.com.cleartech.pgmc.mgu.converters;

import java.lang.reflect.Method;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.ReflectionUtils;

public class AutocompleteConverterFactory implements ConverterFactory<Long, Object> {

	@Override
	public <T> Converter<Long, T> getConverter( Class<T> targetType ) {
		return new AutocompleteConverter<T>( targetType );
	}

	private final class AutocompleteConverter<T extends Object> implements Converter<Long, T> {

		private Class<T> classType;

		public AutocompleteConverter( Class<T> targetType ) {
			this.classType = targetType;
		}

		public T convert( Long id ) {
			T entity = null;
			try {
				entity = classType.newInstance();
			} catch ( InstantiationException e ) {
				e.printStackTrace();
			} catch ( IllegalAccessException e ) {
				e.printStackTrace();
			}

			Method method = ReflectionUtils.findMethod( classType, "setId" );

			if ( method == null || entity == null )
				return null;

			ReflectionUtils.invokeMethod( method, entity, id );
			return entity;
		}
	}

}