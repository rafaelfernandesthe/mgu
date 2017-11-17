package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPerfil is a Querydsl query type for Perfil
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPerfil extends EntityPathBase<Perfil> {

    private static final long serialVersionUID = -2107784869L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPerfil perfil = new QPerfil("perfil");

    public final ListPath<AcessoOperadora, QAcessoOperadora> acessoOperadoras = this.<AcessoOperadora, QAcessoOperadora>createList("acessoOperadoras", AcessoOperadora.class, QAcessoOperadora.class, PathInits.DIRECT2);

    public final StringPath dcDescricao = createString("dcDescricao");

    public final StringPath dcPerfil = createString("dcPerfil");

    public final ListPath<GrupoPerfil, QGrupoPerfil> grupoPerfis = this.<GrupoPerfil, QGrupoPerfil>createList("grupoPerfis", GrupoPerfil.class, QGrupoPerfil.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSistema sistema;

    public final ListPath<Usuario, QUsuario> usuarios = this.<Usuario, QUsuario>createList("usuarios", Usuario.class, QUsuario.class, PathInits.DIRECT2);

    public QPerfil(String variable) {
        this(Perfil.class, forVariable(variable), INITS);
    }

    public QPerfil(Path<? extends Perfil> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPerfil(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPerfil(PathMetadata metadata, PathInits inits) {
        this(Perfil.class, metadata, inits);
    }

    public QPerfil(Class<? extends Perfil> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sistema = inits.isInitialized("sistema") ? new QSistema(forProperty("sistema")) : null;
    }

}

