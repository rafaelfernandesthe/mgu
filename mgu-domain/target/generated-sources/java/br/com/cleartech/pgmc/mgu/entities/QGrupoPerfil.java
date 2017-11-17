package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGrupoPerfil is a Querydsl query type for GrupoPerfil
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGrupoPerfil extends EntityPathBase<GrupoPerfil> {

    private static final long serialVersionUID = 1829222758L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGrupoPerfil grupoPerfil = new QGrupoPerfil("grupoPerfil");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath noGrupoPerfil = createString("noGrupoPerfil");

    public final ListPath<Perfil, QPerfil> perfis = this.<Perfil, QPerfil>createList("perfis", Perfil.class, QPerfil.class, PathInits.DIRECT2);

    public final QPrestadora prestadora;

    public final ListPath<Usuario, QUsuario> usuarios = this.<Usuario, QUsuario>createList("usuarios", Usuario.class, QUsuario.class, PathInits.DIRECT2);

    public QGrupoPerfil(String variable) {
        this(GrupoPerfil.class, forVariable(variable), INITS);
    }

    public QGrupoPerfil(Path<? extends GrupoPerfil> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGrupoPerfil(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGrupoPerfil(PathMetadata metadata, PathInits inits) {
        this(GrupoPerfil.class, metadata, inits);
    }

    public QGrupoPerfil(Class<? extends GrupoPerfil> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prestadora = inits.isInitialized("prestadora") ? new QPrestadora(forProperty("prestadora"), inits.get("prestadora")) : null;
    }

}

