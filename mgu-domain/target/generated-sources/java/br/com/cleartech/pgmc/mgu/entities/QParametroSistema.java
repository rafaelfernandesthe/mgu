package br.com.cleartech.pgmc.mgu.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParametroSistema is a Querydsl query type for ParametroSistema
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QParametroSistema extends EntityPathBase<ParametroSistema> {

    private static final long serialVersionUID = -378209416L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParametroSistema parametroSistema = new QParametroSistema("parametroSistema");

    public final NumberPath<Integer> bloquearInatividade = createNumber("bloquearInatividade", Integer.class);

    public final NumberPath<Integer> flAcessoSimultaneo = createNumber("flAcessoSimultaneo", Integer.class);

    public final NumberPath<Integer> flBloquearInatividade = createNumber("flBloquearInatividade", Integer.class);

    public final NumberPath<Integer> flPrazoExpirarSenha = createNumber("flPrazoExpirarSenha", Integer.class);

    public final NumberPath<Integer> flQtdErrarSenha = createNumber("flQtdErrarSenha", Integer.class);

    public final QGrupoPrestadora grupoPrestadora;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> prazoExpirarSenha = createNumber("prazoExpirarSenha", Integer.class);

    public final NumberPath<Integer> qtdErrarSenha = createNumber("qtdErrarSenha", Integer.class);

    public QParametroSistema(String variable) {
        this(ParametroSistema.class, forVariable(variable), INITS);
    }

    public QParametroSistema(Path<? extends ParametroSistema> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParametroSistema(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParametroSistema(PathMetadata metadata, PathInits inits) {
        this(ParametroSistema.class, metadata, inits);
    }

    public QParametroSistema(Class<? extends ParametroSistema> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.grupoPrestadora = inits.isInitialized("grupoPrestadora") ? new QGrupoPrestadora(forProperty("grupoPrestadora")) : null;
    }

}

