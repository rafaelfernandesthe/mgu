package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAcessoOperadora is a Querydsl query type for AcessoOperadora
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAcessoOperadora extends EntityPathBase<AcessoOperadora> {

    private static final long serialVersionUID = -759239086L;

    public static final QAcessoOperadora acessoOperadora = new QAcessoOperadora("acessoOperadora");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Perfil, QPerfil> perfilAcessoOperadora = this.<Perfil, QPerfil>createList("perfilAcessoOperadora", Perfil.class, QPerfil.class, PathInits.DIRECT2);

    public final EnumPath<br.com.cleartech.pgmc.mgu.enums.TipoOperadora> tipoOperadora = createEnum("tipoOperadora", br.com.cleartech.pgmc.mgu.enums.TipoOperadora.class);

    public QAcessoOperadora(String variable) {
        super(AcessoOperadora.class, forVariable(variable));
    }

    public QAcessoOperadora(Path<? extends AcessoOperadora> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAcessoOperadora(PathMetadata metadata) {
        super(AcessoOperadora.class, metadata);
    }

}

