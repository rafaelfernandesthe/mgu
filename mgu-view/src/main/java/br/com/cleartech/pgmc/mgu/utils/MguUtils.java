package br.com.cleartech.pgmc.mgu.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.ListUtils;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;

public class MguUtils {

	static Gson gson = new Gson();

	public static List<ValueObject> getVO( List<?> list, String valueFieldName, String labelFieldName ) {
		List<ValueObject> result = new ArrayList<>();

		if ( !ListUtils.isEmpty( list ) && !StringUtils.isEmpty( valueFieldName ) && !StringUtils.isEmpty( labelFieldName ) ) {
			Object model = list.get( 0 );

			Field value = ReflectionUtils.findField( model.getClass(), valueFieldName );
			value.setAccessible( true );
			Field label = ReflectionUtils.findField( model.getClass(), labelFieldName );
			label.setAccessible( true );

			for ( Object target : list ) {
				result.add( new ValueObject( String.valueOf( ReflectionUtils.getField( value, target ) ), String.valueOf( ReflectionUtils.getField( label, target ) ) ) );
			}
		}

		return result;
	}

	public static String getVO2JSON( List<?> list, String valueFieldName, String labelFieldName ) {
		return getJSON( getVO( list, valueFieldName, labelFieldName ) );
	}

	public static String getVO2JSON( Iterable<?> list, String valueFieldName, String labelFieldName ) {
		return getJSON( getVO( Lists.newArrayList( list ), valueFieldName, labelFieldName ) );
	}

	public static String getJSON( Object o ) {
		return gson.toJson( o );
	}

	public static List<GrupoPerfil> idListToGrupoPerfilList( List<Integer> listId ) {
		List<GrupoPerfil> result = new ArrayList<GrupoPerfil>();
		for ( Integer id : listId ) {
			result.add( new GrupoPerfil( Long.parseLong( id.toString() ), "" ) );
		}
		return result;
	}

}
