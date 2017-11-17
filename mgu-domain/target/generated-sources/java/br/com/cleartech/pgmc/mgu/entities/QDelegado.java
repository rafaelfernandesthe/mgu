package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDelegado is a Querydsl query type for Delegado
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDelegado extends EntityPathBase<Delegado> {

    private static final long serialVersionUID = 2031651694L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDelegado delegado = new QDelegado("delegado");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPrestadora prestadora;

    public final QUsuario usuarioComum;

    public final QUsuario usuarioMaster;

    public QDelegado(String variable) {
        this(Delegado.class, forVariable(variable), INITS);
    }

    public QDelegado(Path<? extends Delegado> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDelegado(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDelegado(PathMetadata metadata, PathInits inits) {
        this(Delegado.class, metadata, inits);
    }

    public QDelegado(Class<? extends Delegado> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prestadora = inits.isInitialized("prestadora") ? new QPrestadora(forProperty("prestadora"), inits.get("prestadora")) : null;
        this.usuarioComum = inits.isInitialized("usuarioComum") ? new QUsuario(forProperty("usuarioComum"), inits.get("usuarioComum")) : null;
        this.usuarioMaster = inits.isInitialized("usuarioMaster") ? new QUsuario(forProperty("usuarioMaster"), inits.get("usuarioMaster")) : null;
    }

}

