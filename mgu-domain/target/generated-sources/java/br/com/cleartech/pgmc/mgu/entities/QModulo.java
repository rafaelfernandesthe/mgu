package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QModulo is a Querydsl query type for Modulo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QModulo extends EntityPathBase<Modulo> {

    private static final long serialVersionUID = 2110127621L;

    public static final QModulo modulo = new QModulo("modulo");

    public final StringPath dcModulo = createString("dcModulo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Prestadora, QPrestadora> prestadoras = this.<Prestadora, QPrestadora>createList("prestadoras", Prestadora.class, QPrestadora.class, PathInits.DIRECT2);

    public QModulo(String variable) {
        super(Modulo.class, forVariable(variable));
    }

    public QModulo(Path<? extends Modulo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModulo(PathMetadata metadata) {
        super(Modulo.class, metadata);
    }

}

