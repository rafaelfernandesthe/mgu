package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrestadora is a Querydsl query type for Prestadora
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrestadora extends EntityPathBase<Prestadora> {

    private static final long serialVersionUID = -1280546266L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrestadora prestadora = new QPrestadora("prestadora");

    public final ListPath<Delegado, QDelegado> delegados = this.<Delegado, QDelegado>createList("delegados", Delegado.class, QDelegado.class, PathInits.DIRECT2);

    public final QGrupoPrestadora grupoPrestadora;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Modulo, QModulo> modulos = this.<Modulo, QModulo>createList("modulos", Modulo.class, QModulo.class, PathInits.DIRECT2);

    public final StringPath noPrestadora = createString("noPrestadora");

    public final ListPath<Usuario, QUsuario> usuarios = this.<Usuario, QUsuario>createList("usuarios", Usuario.class, QUsuario.class, PathInits.DIRECT2);

    public QPrestadora(String variable) {
        this(Prestadora.class, forVariable(variable), INITS);
    }

    public QPrestadora(Path<? extends Prestadora> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrestadora(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrestadora(PathMetadata metadata, PathInits inits) {
        this(Prestadora.class, metadata, inits);
    }

    public QPrestadora(Class<? extends Prestadora> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.grupoPrestadora = inits.isInitialized("grupoPrestadora") ? new QGrupoPrestadora(forProperty("grupoPrestadora")) : null;
    }

}

