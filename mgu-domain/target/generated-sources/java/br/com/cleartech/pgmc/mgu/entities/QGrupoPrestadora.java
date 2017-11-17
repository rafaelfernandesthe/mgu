package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGrupoPrestadora is a Querydsl query type for GrupoPrestadora
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGrupoPrestadora extends EntityPathBase<GrupoPrestadora> {

    private static final long serialVersionUID = -919247695L;

    public static final QGrupoPrestadora grupoPrestadora = new QGrupoPrestadora("grupoPrestadora");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath noGrupoPrestadora = createString("noGrupoPrestadora");

    public final ListPath<Prestadora, QPrestadora> prestadoras = this.<Prestadora, QPrestadora>createList("prestadoras", Prestadora.class, QPrestadora.class, PathInits.DIRECT2);

    public QGrupoPrestadora(String variable) {
        super(GrupoPrestadora.class, forVariable(variable));
    }

    public QGrupoPrestadora(Path<? extends GrupoPrestadora> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGrupoPrestadora(PathMetadata metadata) {
        super(GrupoPrestadora.class, metadata);
    }

}

