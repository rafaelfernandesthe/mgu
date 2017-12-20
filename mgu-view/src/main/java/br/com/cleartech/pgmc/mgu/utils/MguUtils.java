package br.com.cleartech.pgmc.mgu.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.ListUtils;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import br.com.cleartech.pgmc.mgu.configs.MguAuthenticationProvider.MguUserDetails;
import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;

public class MguUtils {

	static Gson gson = new Gson();
	static int strategy = 1;// by get method

	public static List<ValueObject> getVO( List<?> list, String valueFieldName, String labelFieldName ) {
		List<ValueObject> result = new ArrayList<>();

		if ( !ListUtils.isEmpty( list ) && !StringUtils.isEmpty( valueFieldName ) && !StringUtils.isEmpty( labelFieldName ) ) {
			Object model = list.get( 0 );

			if ( strategy == 1 ) {
				Method valueMethod = ReflectionUtils.findMethod( model.getClass(), "get" + valueFieldName.substring( 0, 1 ).toUpperCase() + valueFieldName.substring( 1 ) );
				Method labelMethod = ReflectionUtils.findMethod( model.getClass(), "get" + labelFieldName.substring( 0, 1 ).toUpperCase() + labelFieldName.substring( 1 ) );
				for ( Object target : list ) {
					result.add( new ValueObject( String.valueOf( ReflectionUtils.invokeMethod( valueMethod, target ) ), String.valueOf( ReflectionUtils.invokeMethod( labelMethod, target ) ) ) );
				}
			} else {
				Field value = ReflectionUtils.findField( model.getClass(), valueFieldName );
				value.setAccessible( true );
				Field label = ReflectionUtils.findField( model.getClass(), labelFieldName );
				label.setAccessible( true );

				for ( Object target : list ) {
					result.add( new ValueObject( String.valueOf( ReflectionUtils.getField( value, target ) ), String.valueOf( ReflectionUtils.getField( label, target ) ) ) );
				}
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

	public static MguUserDetails getUsuarioLogado() {
		return (MguUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
