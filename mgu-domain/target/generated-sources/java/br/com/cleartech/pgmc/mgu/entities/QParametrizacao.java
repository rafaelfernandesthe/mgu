package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QParametrizacao is a Querydsl query type for Parametrizacao
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QParametrizacao extends EntityPathBase<Parametrizacao> {

    private static final long serialVersionUID = -1664895354L;

    public static final QParametrizacao parametrizacao = new QParametrizacao("parametrizacao");

    public final NumberPath<Long> cdParametro = createNumber("cdParametro", Long.class);

    public final StringPath dcParametro = createString("dcParametro");

    public final StringPath vlParametro = createString("vlParametro");

    public QParametrizacao(String variable) {
        super(Parametrizacao.class, forVariable(variable));
    }

    public QParametrizacao(Path<? extends Parametrizacao> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParametrizacao(PathMetadata metadata) {
        super(Parametrizacao.class, metadata);
    }

}

