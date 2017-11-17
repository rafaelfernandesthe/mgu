package br.com.cleartech.pgmc.mgu.repositories.util;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;

public class QuerydslJpaRepositoryAux<T, ID extends Serializable> extends QueryDslJpaRepository<T, ID> {

	private final EntityManager em;

	private final Class<T> entityClass;

	private final JpaEntityInformation<T, ID> entityInformation;

	private final EntityPath<T> path;

	private final PathBuilder<T> builder;

	public QuerydslJpaRepositoryAux( Class<T> entityClass, EntityManager entityManager ) {
		this( entityClass, new JpaMetamodelEntityInformation<T, ID>( entityClass, entityManager.getMetamodel() ), entityManager );
	}

	public QuerydslJpaRepositoryAux( Class<T> entityClass, JpaEntityInformation<T, ID> entityMetadata, EntityManager entityManager ) {
		super( entityMetadata, entityManager );

		this.path = SimpleEntityPathResolver.INSTANCE.createPath( entityMetadata.getJavaType() );
		this.em = entityManager;
		this.entityClass = entityClass;
		this.entityInformation = entityMetadata;

		this.builder = new PathBuilder<T>( path.getType(), path.getMetadata() );
	}

	public EntityManager getEm() {
		return em;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public JpaEntityInformation<T, ID> getEntityInformation() {
		return entityInformation;
	}

	public EntityPath<T> getPath() {
		return path;
	}

	public PathBuilder<T> getBuilder() {
		return builder;
	}

}
