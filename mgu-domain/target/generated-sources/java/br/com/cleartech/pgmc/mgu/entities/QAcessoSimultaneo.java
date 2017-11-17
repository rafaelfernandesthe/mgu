package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAcessoSimultaneo is a Querydsl query type for AcessoSimultaneo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAcessoSimultaneo extends EntityPathBase<AcessoSimultaneo> {

    private static final long serialVersionUID = 969018296L;

    public static final QAcessoSimultaneo acessoSimultaneo = new QAcessoSimultaneo("acessoSimultaneo");

    public final DateTimePath<java.util.Date> dtAcesso = createDateTime("dtAcesso", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath sistema = createString("sistema");

    public final StringPath username = createString("username");

    public QAcessoSimultaneo(String variable) {
        super(AcessoSimultaneo.class, forVariable(variable));
    }

    public QAcessoSimultaneo(Path<? extends AcessoSimultaneo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAcessoSimultaneo(PathMetadata metadata) {
        super(AcessoSimultaneo.class, metadata);
    }

}

