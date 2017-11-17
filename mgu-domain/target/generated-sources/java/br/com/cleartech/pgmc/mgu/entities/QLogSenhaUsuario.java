package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLogSenhaUsuario is a Querydsl query type for LogSenhaUsuario
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogSenhaUsuario extends EntityPathBase<LogSenhaUsuario> {

    private static final long serialVersionUID = 746518990L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLogSenhaUsuario logSenhaUsuario = new QLogSenhaUsuario("logSenhaUsuario");

    public final DateTimePath<java.sql.Timestamp> dataAlteracao = createDateTime("dataAlteracao", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ipAlteracao = createString("ipAlteracao");

    public final StringPath nomeUsuarioAlteracao = createString("nomeUsuarioAlteracao");

    public final QUsuario usuario;

    public QLogSenhaUsuario(String variable) {
        this(LogSenhaUsuario.class, forVariable(variable), INITS);
    }

    public QLogSenhaUsuario(Path<? extends LogSenhaUsuario> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLogSenhaUsuario(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLogSenhaUsuario(PathMetadata metadata, PathInits inits) {
        this(LogSenhaUsuario.class, metadata, inits);
    }

    public QLogSenhaUsuario(Class<? extends LogSenhaUsuario> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.usuario = inits.isInitialized("usuario") ? new QUsuario(forProperty("usuario"), inits.get("usuario")) : null;
    }

}

