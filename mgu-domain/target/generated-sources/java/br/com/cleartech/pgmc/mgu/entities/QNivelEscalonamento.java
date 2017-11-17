package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNivelEscalonamento is a Querydsl query type for NivelEscalonamento
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNivelEscalonamento extends EntityPathBase<NivelEscalonamento> {

    private static final long serialVersionUID = 1008820796L;

    public static final QNivelEscalonamento nivelEscalonamento = new QNivelEscalonamento("nivelEscalonamento");

    public final StringPath dcNivelEscalonamento = createString("dcNivelEscalonamento");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> nuNivelEscalonamento = createNumber("nuNivelEscalonamento", Long.class);

    public final SetPath<Usuario, QUsuario> usuarios = this.<Usuario, QUsuario>createSet("usuarios", Usuario.class, QUsuario.class, PathInits.DIRECT2);

    public QNivelEscalonamento(String variable) {
        super(NivelEscalonamento.class, forVariable(variable));
    }

    public QNivelEscalonamento(Path<? extends NivelEscalonamento> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNivelEscalonamento(PathMetadata metadata) {
        super(NivelEscalonamento.class, metadata);
    }

}

